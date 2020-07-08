package com.example.bubblelayout.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bubblelayout.R
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.viewmodel.HomeViewModel

class HomeFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_home
    lateinit var homeViewModel: HomeViewModel

    override fun initData() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        homeViewModel.dataListLiveData.observe(this, Observer {

        })
        homeViewModel.getNodes()
    }

}