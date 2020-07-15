package com.example.bubblelayout.ui.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.ui.UserInfoActivity
import com.example.bubblelayout.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment() {
    private lateinit var mViewModel: LoginViewModel
    override fun getLayoutId(): Int = R.layout.fragment_account

    override fun initData() {
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        rlUser.setOnClickListener {

        }
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.userLiveData.observe(this, Observer {
            mTvNickname.text = it.nickname
        })
        mViewModel.getUserInfo()
        rlUser.setOnClickListener {
            startActivity(Intent(context, UserInfoActivity::class.java))
        }
    }
}