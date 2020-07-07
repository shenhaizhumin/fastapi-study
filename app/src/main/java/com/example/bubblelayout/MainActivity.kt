package com.example.bubblelayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import com.example.bubblelayout.base.BaseActivity
import com.example.bubblelayout.databinding.ActivityDataBindingBinding
import com.example.bubblelayout.ui.HomeActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_data_binding.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    val TAG = javaClass.simpleName

    //    private lateinit var dataBinding: ActivityDataBindingBinding
    private var isAccount = false
    private var isPassword = false
    private var isProtocol = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_data_binding)
//        dataBinding = DataBindingUtil.setContentView(
//            this,
//            R.layout.activity_data_binding
//        )
        setContentView(R.layout.activity_main)
        etAccount.afterTextChanged {
            isAccount = it?.length!! > 0
        }
        etPassword.afterTextChanged {
            isPassword = it?.length!! > 0
        }
        cbSelect.setOnCheckedChangeListener { _, isChecked ->
            isProtocol = isChecked
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
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
    }

    private fun login() {

    }
}
