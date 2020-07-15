package com.example.bubblelayout.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R

class TargsAdapter(val layoutId: Int, dataList: MutableList<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val tvTag = holder.getView<TextView>(R.id.tv_tag_content)
        tvTag.text = item
    }

}