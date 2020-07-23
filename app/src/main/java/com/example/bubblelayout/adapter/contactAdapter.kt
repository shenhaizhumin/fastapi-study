package com.example.bubblelayout.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.UserEntity
import com.example.bubblelayout.utils.CornerTransform

class contactAdapter(layoutId: Int, dataList: MutableList<UserEntity>?) :
    BaseQuickAdapter<UserEntity, BaseViewHolder>(layoutId, dataList) {
    lateinit var transform: CornerTransform
    override fun convert(holder: BaseViewHolder, item: UserEntity) {
        Glide.with(context).load(item.avatar_url)
            .apply(
                RequestOptions().transform(transform).error(R.mipmap.ic_launcher).placeholder(
                    R.mipmap.ic_launcher
                )
            )
            .into(holder.getView(R.id.iv_item_user_icon))
        holder.setText(R.id.tv_item_username, item.nickname)
    }
}