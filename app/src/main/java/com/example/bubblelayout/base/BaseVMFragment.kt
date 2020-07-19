package com.example.bubblelayout.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

abstract class BaseVMFragment<VM : BaseViewModel> : BaseFragment() {
    lateinit var mViewModel: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mViewModel = ViewModelProvider(this).get(createViewModel())
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun createViewModel(): Class<VM>

}