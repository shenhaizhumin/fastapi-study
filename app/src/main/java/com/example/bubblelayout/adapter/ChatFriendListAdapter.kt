package com.example.bubblelayout.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.entity.UserEntity
import com.example.bubblelayout.utils.CornerTransform
import java.text.SimpleDateFormat
import java.util.*

class ChatFriendListAdapter(layoutId: Int, dataList: MutableList<ChatMessageEntity>?) :
    BaseQuickAdapter<ChatMessageEntity, BaseViewHolder>(layoutId, dataList) {
    lateinit var transform: CornerTransform
    override fun convert(holder: BaseViewHolder, item: ChatMessageEntity) {
        Glide.with(context).load(item.friend_avatar_url)
            .apply(
                RequestOptions().transform(transform).error(R.mipmap.ic_launcher).placeholder(
                    R.mipmap.ic_launcher
                )
            )
            .into(holder.getView(R.id.iv_item_friend_icon))
        holder.setText(R.id.tv_item_friend_nickname, item.friend_nickname)
            .setText(R.id.tv_item_latest_msg, item.content)
            .setText(R.id.tv_item_post_time, SimpleDateFormat("HH:mm").format(Date(item.post_date)))
    }
}