package com.example.bubblelayout.ui.fragment

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bubblelayout.R
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.ui.UserInfoActivity
import com.example.bubblelayout.utils.CornerTransform
import com.example.bubblelayout.utils.GlideEngine
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.LoginViewModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.tools.ScreenUtils
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_moment.*
import java.io.File


class AccountFragment : BaseVMFragment<LoginViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_account
    private lateinit var transform: CornerTransform

    override fun initData() {
        transform = CornerTransform(context, ScreenUtils.dip2px(context, 4f).toFloat())
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.userLiveData.observe(this, Observer {
            mTvNickname.text = it.nickname
            if (!TextUtils.isEmpty(it.avatar_url)) {
                Glide.with(context!!).asBitmap()
                    .load(UserInfoUtil.getUserAvatar())
                    .apply(
                        RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                            .transform(transform)
                    )
                    .into(mIvUserIcon)
            }
        })
        mViewModel.fileLiveData.observe(this, Observer {
            mViewModel.updateUser(
                UserBody(
                    null,
                    null,
                    null,
                    null,
                    null,
                    it.file_url
                )
            )
        })
        mViewModel.getUserInfo()
        mTvNickname.setOnClickListener {
            startActivity(Intent(context, UserInfoActivity::class.java))
        }
        mIvUserIcon.setOnClickListener {
            PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .imageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .forResult(PictureConfig.CHOOSE_REQUEST)
        }
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA
            ),
            200
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                val selectList =
                    PictureSelector.obtainMultipleResult(data)
                mViewModel.uploadFile(File(selectList[0].realPath))
            }
        }
    }

    override fun createViewModel(): Class<LoginViewModel> = LoginViewModel::class.java
}