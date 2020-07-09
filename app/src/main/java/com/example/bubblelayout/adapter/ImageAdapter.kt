package com.example.bubblelayout.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R

class ImageAdapter(layoutId: Int, dataList: MutableList<String>?) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: String) {
        val imageView = holder.getView<ImageView>(R.id.iv_image)
        Glide.with(context).asBitmap().load(item).into(imageView)
    }
}