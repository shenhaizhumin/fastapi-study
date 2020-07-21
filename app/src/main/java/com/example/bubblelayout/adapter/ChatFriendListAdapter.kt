package com.example.bubblelayout.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.entity.UserEntity
import java.text.SimpleDateFormat

class ChatFriendListAdapter(layoutId: Int, dataList: MutableList<ChatMessageEntity>?) :
    BaseQuickAdapter<ChatMessageEntity, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: ChatMessageEntity) {
        Glide.with(context).load(item.friend_avatar_url)
            .into(holder.getView(R.id.iv_item_user_icon))
        holder.setText(R.id.tv_item_friend_nickname, item.friend_nickname)
            .setText(R.id.tv_item_latest_msg, item.content)
            .setText(R.id.tv_item_post_time, SimpleDateFormat("HH:mm").format(item.post_date))
    }
}