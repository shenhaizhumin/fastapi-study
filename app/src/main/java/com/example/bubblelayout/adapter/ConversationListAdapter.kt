package com.example.bubblelayout.adapter

import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.ConversationEntity
import com.example.bubblelayout.utils.CornerTransform
import java.text.SimpleDateFormat
import java.util.*

class ConversationListAdapter(layoutId: Int, dataList: MutableList<ConversationEntity>?) :
    BaseQuickAdapter<ConversationEntity, BaseViewHolder>(layoutId, dataList) {
    lateinit var transform: CornerTransform
    override fun convert(holder: BaseViewHolder, item: ConversationEntity) {
        Glide.with(context).load(item.portraitUrl)
            .apply(
                RequestOptions().transform(transform).error(R.mipmap.ic_launcher).placeholder(
                    R.mipmap.ic_launcher
                )
            )
            .into(holder.getView(R.id.iv_item_friend_icon))
        val time:Long?=item.receivedTime?:item.sentTime
        holder.setText(R.id.tv_item_friend_nickname, item.conversationTitle)
            .setText(R.id.tv_item_latest_msg, item.latestMessage)
            .setText(R.id.tv_item_post_time, SimpleDateFormat("HH:mm").format(Date(time?:0)))
    }
}