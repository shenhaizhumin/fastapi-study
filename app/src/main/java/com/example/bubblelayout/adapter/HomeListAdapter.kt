package com.example.bubblelayout.adapter

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.ScreenUtils
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.HomeDataEntity
import java.util.*

class HomeListAdapter(val context: Context, val dataList: MutableList<HomeDataEntity>?) :
    RecyclerView.Adapter<HomeListAdapter.MyViewHolder>() {
    val TAG = javaClass.simpleName
    private val random = Random()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage = itemView.findViewById<ImageView>(R.id.iv_item_img)
        val itemTitle = itemView.findViewById<TextView>(R.id.tv_item_title)
        val itemDesc = itemView.findViewById<TextView>(R.id.tv_item_desc)
        val itemDownloads=itemView.findViewById<TextView>(R.id.tv_item_downloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false))

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        val defaultDisplay = (context as Activity).windowManager.defaultDisplay
//        val point = Point()
//        defaultDisplay.getSize(point)
//        val screenWidth = point.x
//        val screenHeight = point.y
//        ScreenUtils.getAppScreenWidth()
//        val width = ScreenUtils.getScreenWidth() - 20 * 3
        val width = ScreenUtils.getAppScreenWidth() - 20 * 3
        val item = dataList?.get(position)
        val layoutParams = holder.itemImage.layoutParams
        if (item?.height == null) {
            var height = layoutParams.height
            height += random.nextInt(400)
            layoutParams.height = height
            layoutParams.width = width / 2
            holder.itemImage.layoutParams = layoutParams
            item?.height = height
        } else {
            layoutParams.height = item.height
            holder.itemImage.layoutParams = layoutParams
        }
//        Log.e(TAG, "height:${item?.height}")
        Log.e(TAG, "width:${holder.itemImage.layoutParams.width}")

    }
}