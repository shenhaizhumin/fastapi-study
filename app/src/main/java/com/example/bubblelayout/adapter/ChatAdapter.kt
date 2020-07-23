package com.example.bubblelayout.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.entity.UserEntity
import com.example.bubblelayout.utils.CornerTransform
import java.text.SimpleDateFormat

class ChatAdapter(layoutId: Int, dataList: MutableList<ChatMessageEntity>?) :
    BaseQuickAdapter<ChatMessageEntity, BaseViewHolder>(layoutId, dataList) {

    lateinit var transform: CornerTransform
    var mineAvatarUrl: String = ""
    var friendAvatarUrl: String = ""
    override fun convert(holder: BaseViewHolder, item: ChatMessageEntity) {
        holder.setText(R.id.chat_item_date, SimpleDateFormat("HH:mm").format(item.post_date))
        val leftImageView = holder.getView<ImageView>(R.id.chat_item_left_header)
        val rightImageView = holder.getView<ImageView>(R.id.chat_item_right_header)
        val leftTv = holder.getView<TextView>(R.id.tv_item_left_content)
        val rightTv = holder.getView<TextView>(R.id.tv_item_right_content)
        if (item.ismineChat == 0) {
            //mine chat
            rightImageView.visibility = View.VISIBLE
            rightTv.visibility = View.VISIBLE
            leftTv.visibility = View.GONE
            leftImageView.visibility = View.GONE
            Glide.with(context).load(mineAvatarUrl)
                .apply(
                    RequestOptions().transform(transform).error(R.mipmap.ic_launcher).placeholder(
                        R.mipmap.ic_launcher
                    )
                )
                .into(rightImageView)
            rightTv.text = "${item.content}"
        } else {
            rightImageView.visibility = View.GONE
            rightTv.visibility = View.GONE

            leftTv.visibility = View.VISIBLE
            leftImageView.visibility = View.VISIBLE
            Glide.with(context).load(friendAvatarUrl)
                .apply(
                    RequestOptions().transform(transform).error(R.mipmap.ic_launcher).placeholder(
                        R.mipmap.ic_launcher
                    )
                )
                .into(leftImageView)
            leftTv.text = "${item.content}"
        }
//        Glide.with(context).load(item.friend_avatar_url)
//            .into(holder.getView(R.id.iv_item_user_icon))
//        holder.setText(R.id.tv_item_friend_nickname, item.friend_nickname)
//            .setText(R.id.tv_item_latest_msg, item.content)
//            .setText(R.id.tv_item_post_time, SimpleDateFormat("HH:mm").format(item.post_date))
    }
}