package com.example.bubblelayout.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.base.BaseActivity
import com.example.bubblelayout.utils.UserInfoUtil
import com.example.bubblelayout.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity() {
    val TAG = javaClass.simpleName

    //    private lateinit var dataBinding: ActivityDataBindingBinding
    private var isAccount = false
    private var isPassword = false
    private var isProtocol = false
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_binding)
//        dataBinding = DataBindingUtil.setContentView(
//            this,
//            R.layout.activity_data_binding
//        )
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.userLiveData.observe(this, Observer {
            //登录成功！
            UserInfoUtil.setUserId(it.id)
            UserInfoUtil.setAccessToken("Bearer ${it.access_token}")
            startActivity(Intent(this, HomeActivity::class.java))
        })
        loginViewModel.errorLiveData.observe(this, Observer {
            toast("$it")
        })
        etAccount.afterTextChanged {
            isAccount = it?.length!! > 0
        }
        etPassword.afterTextChanged {
            isPassword = it?.length!! > 0
        }
        cbSelect.setOnCheckedChangeListener { _, isChecked ->
            isProtocol = isChecked
        }
        mTvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
//            startActivity(Intent(this, HomeActivity::class.java))
            if (!isAccount) {
                toast("missing account")
                return@setOnClickListener
            }
            if (!isPassword) {
                toast("missing password")
                return@setOnClickListener
            }
            if (!isProtocol) {
                toast("missing protocol")
                return@setOnClickListener
            }
            //登录
            login()
        }
        etAccount.setText("19973488682")
        etPassword.setText("123456")
        cbSelect.isSelected = true
    }

    private fun login() {
        loginViewModel.login(
            etAccount.text.toString().trim(),
            etPassword.text.toString().trim()
        )
    }
}
