package com.example.bubblelayout.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.CommentEntity
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(layoutId: Int, dataList: MutableList<CommentEntity>?) :
    BaseQuickAdapter<CommentEntity, BaseViewHolder>(layoutId, dataList) {

    override fun convert(holder: BaseViewHolder, item: CommentEntity) {
        holder.setText(R.id.tv_item_nickName, item.publisher.nickname + "")
            .setText(R.id.tv_item_content, "：${item.content}")
    }

}