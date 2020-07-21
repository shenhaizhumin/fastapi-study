package com.example.bubblelayout.base

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(createViewModel())
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
    }

    abstract fun createViewModel(): Class<VM>
}