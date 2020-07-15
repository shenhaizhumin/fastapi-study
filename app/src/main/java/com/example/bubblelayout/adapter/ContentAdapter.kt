package com.example.bubblelayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.CategoryEntity
import com.example.bubblelayout.entity.ContenEntity
import java.text.SimpleDateFormat

class ContentAdapter(val context: Context, var dataList: MutableList<CategoryEntity>?) :
    RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle = itemView.findViewById<TextView>(R.id.tv_item_title)
        val itemDesc = itemView.findViewById<TextView>(R.id.tv_item_desc)
        val itemDownloads = itemView.findViewById<TextView>(R.id.tv_item_downloads)
        val itemTopic = itemView.findViewById<TextView>(R.id.tv_item_topic)
        val itemRv = itemView.findViewById<RecyclerView>(R.id.rv_item_images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.item_content, parent, false
        )
    )

    fun setNewData(data: MutableList<CategoryEntity>) {
        if (dataList == null) {
            dataList = ArrayList()
        }
        dataList!!.addAll(data)
        notifyDataSetChanged()
    }
//    fun addData

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList!![position]
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.itemRv.layoutManager = layoutManager
        holder.itemTitle.text = item.title
        holder.itemDesc.text = item.desc
        holder.itemTopic.text = item.author
        holder.itemDownloads.text = item.publishedAt
//        val simpleDateFormat= SimpleDateFormat("")
        val adapter = ImageAdapter(R.layout.item_image, item.images)
        adapter.setOnItemClickListener { adapter, view, position ->
            ToastUtils.showShort("条目：${adapter.data.size}")
        }
        holder.itemRv.adapter = adapter
    }
}