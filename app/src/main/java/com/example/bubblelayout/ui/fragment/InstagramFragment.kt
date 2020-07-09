package com.example.bubblelayout.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.ContentPagerAdapter
import com.example.bubblelayout.adapter.TitleIndicatorAdapter
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.viewmodel.DiscoverViewModel
import kotlinx.android.synthetic.main.fragment_instagram.*

class InstagramFragment : BaseFragment() {
    private lateinit var mDiscoverViewModel: DiscoverViewModel
    private val category = "Article"

    //    private val mTitles = listOf("热门", "Android", "App", "iOS", "前端", "拓展资源")
    private val mFragments = ArrayList<Fragment>()
    private lateinit var mAdapter: ContentPagerAdapter

    override fun getLayoutId(): Int = R.layout.fragment_instagram

    override fun initData() {
        mDiscoverViewModel = ViewModelProvider(this).get(DiscoverViewModel::class.java)
        mDiscoverViewModel.categoriesLiveData.observe(this, Observer { list ->
            val titles = list.map { it.title }
            list.forEach { item ->
                val bundle = Bundle()
                bundle.putString("category", category)
                bundle.putString("type", item.type)
                val contentFragment = ContentFragment()
                contentFragment.arguments = bundle
                mFragments.add(contentFragment)
            }
            mAdapter = ContentPagerAdapter(mFragments, childFragmentManager)
            mContentPager.adapter = mAdapter
            mRvIndicator.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val indicatorAdapter = TitleIndicatorAdapter(titles.toMutableList())
            mRvIndicator.adapter = indicatorAdapter
            indicatorAdapter.setItemClick { title, position ->
                Log.e("tag", title)
                mContentPager.setCurrentItem(position, true)
            }
        })

        mDiscoverViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        //Article | GanHuo | Girl
        mDiscoverViewModel.categories(category)
        //禁止viewpager滑动
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
//            mDiscoverViewModel.categories(category)
        }
    }

}