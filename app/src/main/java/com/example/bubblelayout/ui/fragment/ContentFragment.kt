package com.example.bubblelayout.ui.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ContentAdapter
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.entity.ContenEntity
import com.example.bubblelayout.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_content.*

class ContentFragment : BaseVMFragment<DiscoverViewModel>() {
    private lateinit var mContentAdapter: ContentAdapter
    override fun getLayoutId(): Int = R.layout.fragment_content
    private var page = 0
    private val count = 20

    override fun initData() {
        mViewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
        mViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mViewModel.categoryLiveData.observe(this, Observer {
            mContentAdapter.setNewData(it)
        })
        val category = arguments?.getString("category")
        val type = arguments?.getString("type")
        mViewModel.category("$category", "$type", page, count)
        mContentRv.layoutManager = LinearLayoutManager(context)
        mContentAdapter = ContentAdapter(context!!, null)
        mContentRv.adapter = mContentAdapter
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
//            toast(title)
        }
    }

    override fun createViewModel(): Class<DiscoverViewModel> = DiscoverViewModel::class.java
}