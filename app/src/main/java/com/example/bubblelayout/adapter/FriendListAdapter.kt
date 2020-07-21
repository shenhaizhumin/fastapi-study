package com.example.bubblelayout.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.UserEntity

class FriendListAdapter(layoutId: Int, dataList: MutableList<UserEntity>?) :
    BaseQuickAdapter<UserEntity, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: UserEntity) {
        Glide.with(context).load(item.avatar_url)
            .into(holder.getView(R.id.iv_item_user_icon))
        holder.setText(R.id.tv_item_username, item.nickname)
    }
}