package com.example.bubblelayout.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMActivity<VM : BaseViewModel> : BaseActivity() {
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(createViewModel())
    }

    abstract fun createViewModel(): Class<VM>
}