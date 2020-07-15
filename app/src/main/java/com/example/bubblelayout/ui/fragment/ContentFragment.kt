package com.example.bubblelayout.ui.fragment

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ContentAdapter
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.entity.ContenEntity
import com.example.bubblelayout.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_content.*

class ContentFragment : BaseFragment() {
    private lateinit var mContentAdapter: ContentAdapter
    override fun getLayoutId(): Int = R.layout.fragment_content
    private lateinit var mDiscoverViewModel: DiscoverViewModel
    private var page = 0
    private val count = 20

    override fun initData() {
        mDiscoverViewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
        mDiscoverViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        mDiscoverViewModel.categoryLiveData.observe(this, Observer {
            mContentAdapter.setNewData(it)
        })
        val category = arguments?.getString("category")
        val type = arguments?.getString("type")
        mDiscoverViewModel.category("$category", "$type", page, count)
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
}