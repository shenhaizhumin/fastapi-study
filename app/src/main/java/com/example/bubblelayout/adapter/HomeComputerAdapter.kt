package com.example.bubblelayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.HomeDataEntity
import com.google.android.flexbox.FlexboxLayoutManager

class HomeComputerAdapter(val context: Context, val dataList: MutableList<HomeDataEntity>?) :
    RecyclerView.Adapter<HomeComputerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage = itemView.findViewById<ImageView>(R.id.iv_item_img)
        val itemTitle = itemView.findViewById<TextView>(R.id.tv_item_title)
        val itemDesc = itemView.findViewById<TextView>(R.id.tv_item_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
            R.layout.item_home, parent, false
        )
    )

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //            val layoutParams = holder.itemView.layoutParams as FlexboxLayoutManager.LayoutParams
        val layoutParams = holder.itemView.layoutParams
        if (position % 3 == 0) {
            val width = ScreenUtils.getAppScreenWidth() - 20 * 2
            layoutParams.width = width
            holder.itemView.layoutParams = layoutParams
        } else {
            val width = ScreenUtils.getAppScreenWidth() - 20 * 3
            layoutParams.width = width / 2
            holder.itemView.layoutParams = layoutParams
        }

    }
}