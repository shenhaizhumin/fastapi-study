package com.example.bubblelayout.ui.fragment

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bubblelayout.R
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.ui.UserInfoActivity
import com.example.bubblelayout.utils.GlideEngine
import com.example.bubblelayout.viewmodel.LoginViewModel
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import kotlinx.android.synthetic.main.fragment_account.*
import java.io.File


class AccountFragment : BaseFragment() {
    private lateinit var mViewModel: LoginViewModel
    override fun getLayoutId(): Int = R.layout.fragment_account

    override fun initData() {
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.userLiveData.observe(this, Observer {
            mTvNickname.text = it.nickname
            if (!TextUtils.isEmpty(it.avatar_url)) {
                Glide.with(context).load(it.avatar_url).into(mIvUserIcon)
            }
        })
        mViewModel.fileLiveData.observe(this, Observer {
            mViewModel.updateUser(
                UserBody(
                    username = null,
                    mobile = null,
                    password = null,
                    email = null,
                    nickname = null,
                    avatar_url = it.file_url
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
                .forResult(PictureConfig.CHOOSE_REQUEST);
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
}