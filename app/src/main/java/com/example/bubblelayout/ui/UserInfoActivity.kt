package com.example.bubblelayout.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.base.BaseActivity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : BaseActivity() {
    private lateinit var mViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        init()
    }

    private fun init() {
        mViewModel.userLiveData.observe(this, Observer {
            mTvUsername.text = it.username
            mTvNickname.text = it.nickname
            mTvEmail.text = it.email
            mTvMobile.text = it.mobile
        })
        mViewModel.errorLiveData.observe(this, Observer {
            toast("$it")
        })
        mViewModel.getUserInfo()
        mIvBack.setOnClickListener { finish() }
    }
}
