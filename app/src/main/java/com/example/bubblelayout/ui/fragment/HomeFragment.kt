package com.example.bubblelayout.ui.fragment

import android.content.Intent
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.HomeAdapter
import com.example.bubblelayout.adapter.HomeComputerAdapter
import com.example.bubblelayout.adapter.HomeListAdapter
import com.example.bubblelayout.base.BaseFragment
import com.example.bubblelayout.base.BaseVMFragment
import com.example.bubblelayout.entity.HomeDataEntity
import com.example.bubblelayout.ui.SearchActivity
import com.example.bubblelayout.utils.GridSpacingItemDecoration
import com.example.bubblelayout.utils.StaggeredDividerItemDecoration
import com.example.bubblelayout.viewmodel.HomeViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.ivChange

class HomeFragment : BaseVMFragment<HomeViewModel>() {
    override fun getLayoutId(): Int = R.layout.fragment_home
    lateinit var homeViewModel: HomeViewModel
    private lateinit var mPhoneAdapter: HomeListAdapter
    private lateinit var mComputerAdapter: HomeComputerAdapter
    private var isPhone = true
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var itemDecoration: GridSpacingItemDecoration

    //    private lateinit var flexboxLayoutManager:FlexboxLayoutManager
    private lateinit var flexboxLayoutManager: GridLayoutManager

    override fun initData() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.errorLiveData.observe(this, Observer {
            toast(it)
        })
        homeViewModel.dataListLiveData.observe(this, Observer {

        })
        homeViewModel.getNodes()
        itemDecoration = GridSpacingItemDecoration(2, 20, true)
        staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        flexboxLayoutManager= FlexboxLayoutManager( context,FlexDirection.ROW)
        flexboxLayoutManager = GridLayoutManager(context, 2)
        flexboxLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                //position 0 占用两个位置
                //position 1 占用1个位置 position 2 占用1个位置
                //position 3 占用两个位置
                val x = position % 3
                return if (x == 0) {
                    2
                } else {
                    1
                }

            }
        }
//        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mRvList.layoutManager = staggeredGridLayoutManager
//        layoutManager.gapStrategy=StaggeredGridLayoutManager.GAP_HANDLING_NONE
        val list = mutableListOf(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
        val datas = ArrayList<HomeDataEntity>()
        list.forEach {
            datas.add(HomeDataEntity())
        }
        mPhoneAdapter = HomeListAdapter(context!!, datas)
        mComputerAdapter = HomeComputerAdapter(context!!, datas)
        mRvList.adapter = mPhoneAdapter
        mRvList.itemAnimator = null
        mRvList.addItemDecoration(GridSpacingItemDecoration(2, 20, true))
        mRvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                staggeredGridLayoutManager.invalidateSpanAssignments()////防止第一行到顶部有空白区域
            }
        })
        /**
         * 搜索页面
         */
        rlSearch.setOnClickListener {
            startActivity(Intent(context, SearchActivity::class.java))
        }
        /**
         * 更换数据
         */
        ivChange.setOnClickListener {
            if (isPhone) {
                mRvList.layoutManager = flexboxLayoutManager
                mRvList.adapter = mComputerAdapter
                ivChange.setImageResource(R.drawable.ic_riline_computer_line)
                mRvList.removeItemDecoration(itemDecoration)
            } else {
                mRvList.addItemDecoration(itemDecoration)
                mRvList.layoutManager = staggeredGridLayoutManager
                mRvList.adapter = mPhoneAdapter
                ivChange.setImageResource(R.drawable.ic_riline_tablet_line_copy)
            }
            isPhone = !isPhone
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            homeViewModel.getNodes()
        }
    }

    override fun createViewModel(): Class<HomeViewModel> =HomeViewModel::class.java

}