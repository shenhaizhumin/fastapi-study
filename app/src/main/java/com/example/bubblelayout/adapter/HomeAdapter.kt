package com.example.bubblelayout.adapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import java.util.*

class HomeAdapter(val layoutId: Int, val dataList: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutId, dataList) {
    private val random = Random()

    override fun convert(holder: BaseViewHolder, item: String) {
        val itemImage = holder.getView<ImageView>(R.id.iv_item_img)
        val itemTitle = holder.getView<TextView>(R.id.tv_item_title)
        val itemDesc = holder.getView<TextView>(R.id.tv_item_desc)
        val layoutParams = itemImage.layoutParams
        var height = layoutParams.height
        height += random.nextInt(100)
        layoutParams.height = height
        holder.itemView.layoutParams = layoutParams
//        val url =
//            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1594227489979&di=f1b3ff87e3a46f27874356140b7530dd&imgtype=0&src=http%3A%2F%2Fbos.pgzs.com%2Frbpiczy%2FWallpaper%2F2013%2F2%2F3%2F897cadfc9409480db13ab11f6f61be21-4.jpg"
//        Glide.with(context)
//            .load(url)
//            .into(itemImage)
    }
}