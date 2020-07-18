package com.example.bubblelayout.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.MomentEntity
import com.example.bubblelayout.imageloader.ImageLoader

class MomentAdapter(layoutId: Int, dataList: MutableList<MomentEntity>?) :
    BaseQuickAdapter<MomentEntity, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: MomentEntity) {
        holder.setText(R.id.tv_item_user_name, item.publisher.nickname)
            .setText(R.id.tv_item_content, item.content)
        val itemImg = holder.getView<ImageView>(R.id.iv_item_user_icon)
        ImageLoader.getInstance().loadImage(item.publisher.avatar_url, itemImg)
        val rvComments = holder.getView<RecyclerView>(R.id.rv_item_comments)
        val commentAdapter = CommentAdapter(R.layout.item_comment, item.comments)
        rvComments.adapter = commentAdapter
    }
}