package com.example.bubblelayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bubblelayout.R
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class RvMergeAdapter :
    RecyclerView.Adapter<RvMergeAdapter.ViewHolder> {
    private var context: Context
//    private var layoutManager: FlexboxLayoutManager
    private var lastList: MutableList<String>?
    private var topicList: MutableList<String>?

    constructor(
        context: Context,
        lastList: MutableList<String>?,
        topicList: MutableList<String>?
    ) : super() {
        this.context = context
        this.lastList = lastList
        this.topicList = topicList

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvList = itemView.findViewById<RecyclerView>(R.id.rv_list)
        val tvText = itemView.findViewById<TextView>(R.id.tv_text)
    }

    val type_text_last = 1
    val type_last = 3
    val type_text_topic = 2
    val type_topic = 4


    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                type_text_last
            }
            1 -> {
                type_last
            }
            2 -> {
                type_text_topic
            }
            3 -> {
                type_topic
            }
            else -> {
                super.getItemViewType(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return when (viewType) {
            in 1..2 -> {
                ViewHolder(layoutInflater.inflate(R.layout.item_text, parent, false))
            }
            else -> {
                ViewHolder(layoutInflater.inflate(R.layout.item_rv, parent, false))
            }
        }
    }


    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemViewType = getItemViewType(position)
        if (itemViewType == type_text_last) {
            holder.tvText.text = "最近搜索"
        } else if (itemViewType == type_text_topic) {
            holder.tvText.text = "热门搜索"
        } else {
            val layoutManager = FlexboxLayoutManager(context, FlexDirection.ROW)
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.justifyContent = JustifyContent.FLEX_START
            holder.rvList.layoutManager = layoutManager
            if (itemViewType == type_last) {
                val tagsAdapter = TargsAdapter(R.layout.item_tags, lastList)
                holder.rvList.adapter = tagsAdapter
            } else {
                val tagsAdapter = TargsAdapter(R.layout.item_tags, topicList)
                holder.rvList.adapter = tagsAdapter
            }
        }
    }
}