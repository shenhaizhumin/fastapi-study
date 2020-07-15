package com.example.bubblelayout.adapter

import android.graphics.Color
import android.util.SparseBooleanArray
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R

class TitleIndicatorAdapter(dataList: MutableList<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_content_title, dataList) {
    private val map = SparseBooleanArray().apply {
        this.put(0, true)
    }
    private var prePosition: Int = 0

    lateinit var onItemClick: (title: String, position: Int) -> Unit

    fun setItemClick(action: (title: String, position: Int) -> Unit) {
        onItemClick = action
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        val itemTitle = holder.getView<TextView>(R.id.tv_content_title)
        itemTitle.text = item
        holder.itemView.setOnClickListener {
            val curPosition = holder.absoluteAdapterPosition
            onItemClick(item, curPosition)
            map.put(prePosition, false)
            map.put(curPosition, true)
            notifyItemChanged(curPosition)
            notifyItemChanged(prePosition)
            prePosition = curPosition
        }
        if (map.get(holder.absoluteAdapterPosition)) {
            itemTitle.setTextColor(Color.WHITE)
            itemTitle.setBackgroundResource(R.drawable.shape_title_bg)
        } else {
            itemTitle.setTextColor(Color.BLACK)
            itemTitle.background = null
        }
    }
}