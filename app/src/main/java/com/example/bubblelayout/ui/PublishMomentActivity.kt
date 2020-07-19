package com.example.bubblelayout.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ImageAdapter
import com.example.bubblelayout.adapter.PublishImageAdapter
import com.example.bubblelayout.api.body.MomentBody
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.entity.FileEntity
import com.example.bubblelayout.utils.GlideEngine
import com.example.bubblelayout.viewmodel.MomentViewModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.activity_publish_moment.*
import java.io.File

class PublishMomentActivity : BaseVMActivity<MomentViewModel>() {
    private lateinit var mImageAdapter: PublishImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish_moment)
        init()
    }

    private fun init() {
        mTvCancel.setOnClickListener { finish() }
        mTvPublish.setOnClickListener {
            val images = mImageAdapter.data
            images.removeAt(0)
            //发送
            mViewModel.publishMoment(
                MomentBody(
                    mEtContent.text.toString().trim(), null, images
                )
            )
        }
        mViewModel.mPublishMomentLiveData.observe(this, Observer {
            setResult(200)
            finish()
        })
        val images = ArrayList<FileEntity>()
        images.add(FileEntity(0, ""))
        mImageAdapter = PublishImageAdapter(this, R.layout.item_image_moment_publish, images, {
            //点击删除
            mImageAdapter.remove(it)
            Log.e(TAG, "ids:${mImageAdapter.data}")
        }, {
            //选择图片
            PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .forResult(PictureConfig.CHOOSE_REQUEST)
        })
        mRvImages.adapter = mImageAdapter
        mViewModel.fileLiveData.observe(this, Observer { list ->
            //上传成功
            mImageAdapter.addData(list)
        })
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mEtContent.afterTextChanged {
//            val height = mEtContent.height
            Log.e(TAG, "height:${mEtContent.lineHeight}")

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                val selectList =
                    PictureSelector.obtainMultipleResult(data)
                mViewModel.uploadFiles(selectList.map {
                    return@map File(it.realPath)
                })
            }
        }
    }

    override fun createViewModel(): Class<MomentViewModel> = MomentViewModel::class.java
}
