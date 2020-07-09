package com.example.bubblelayout.ui

import android.os.Bundle
import com.example.bubblelayout.R
import com.example.bubblelayout.adapter.RvMergeAdapter
import com.example.bubblelayout.base.BaseActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {
    private val lastList = ArrayList<String>()
    private val topicList = ArrayList<String>()
    private var isPhone = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        initView()
    }

    private fun initView() {
        mTvCancel.setOnClickListener {
            finish()
        }
        val searchKey = mEtSearchKey.text.toString().trim()
        val layoutManager = FlexboxLayoutManager(this, FlexDirection.ROW)
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.justifyContent = JustifyContent.FLEX_START
        mRvTags.layoutManager = layoutManager
        lastList.addAll(arrayListOf("风景壁纸", "手绘壁纸"))
        topicList.addAll(arrayListOf("风景壁纸", "手绘壁纸"))
        val adapter =
            RvMergeAdapter(
                this,
                lastList,
                topicList
            )
        mRvTags.adapter = adapter
        ivChange.setOnClickListener {
            lastList.clear()
            topicList.clear()
            //电脑或手机壁纸切换
            if (isPhone) {
                lastList.addAll(arrayListOf("风景壁纸电脑", "电脑", "1231", "1222222222222222"))
                topicList.addAll(arrayListOf("风景壁纸电脑", "手绘", "风景壁纸电脑", "风景壁"))
                ivChange.setImageResource(R.drawable.ic_riline_computer_line)
            } else {
                lastList.addAll(arrayListOf("风景壁纸", "手绘壁纸"))
//                topicList.addAll(arrayListOf("风景壁纸", "手绘壁纸"))
                ivChange.setImageResource(R.drawable.ic_riline_tablet_line_copy)
            }
            adapter.notifyDataSetChanged()
            isPhone = !isPhone
        }
    }
}
