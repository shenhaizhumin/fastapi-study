package com.example.bubblelayout.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.base.BaseVMActivity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseVMActivity<LoginViewModel>() {
    override fun createViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        init()
    }

    private fun init() {
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.userLiveData.observe(this, Observer {
            //登录成功！
            UserInfoUtil.setUserId(it.id.toInt())
            UserInfoUtil.setAccessToken("Bearer ${it.access_token}")
            startActivity(Intent(this, HomeActivity::class.java))
        })

        btnRegister.setOnClickListener {
            val username = mEtUsername.text.toString().trim()
            val email = mEtEmail.text.toString().trim()
            val mobile = mEtMobile.text.toString().trim()

            val nickName = mEtNickname.text.toString().trim()
            val password = mEtPassword.text.toString().trim()
            mViewModel.register(UserBody(username, password, email, mobile, nickName, null))
        }
        mIvBack.setOnClickListener {
            finish()
        }

    }
}
