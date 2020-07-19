package com.example.bubblelayout.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.R
import com.example.bubblelayout.entity.MomentEntity
import com.example.bubblelayout.entity.UserMomentEntity
import com.lzy.ninegrid.ImageInfo
import com.lzy.ninegrid.NineGridView
import com.lzy.ninegrid.preview.NineGridViewClickAdapter

class UserMomentAdapter(layoutId: Int, dataList: MutableList<UserMomentEntity>?) :
    BaseQuickAdapter<UserMomentEntity, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: UserMomentEntity) {
        holder.setText(R.id.tv_item_date, item.day_key)
        val itemRv = holder.getView<RecyclerView>(R.id.item_rv_user_moment)
        itemRv.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }

            override fun canScrollHorizontally(): Boolean {
                return false
            }
        }
        val adapter = UserMomentContentAdapter(
            R.layout.item_user_moment_content,
            item.moments.toMutableList()
        )
        itemRv.adapter = adapter
    }
}

class UserMomentContentAdapter(layoutId: Int, dataList: MutableList<MomentEntity>?) :
    BaseQuickAdapter<MomentEntity, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: MomentEntity) {
        val ngv = holder.getView<NineGridView>(R.id.item_ngv)
        holder.setText(R.id.tv_item_image_count, "共${item.images.size}张")
            .setText(R.id.item_tv_content, item.content)
        val tvCount = holder.getView<TextView>(R.id.tv_item_date)
        if (item.images.size > 0) {
            tvCount.text = item.content
            tvCount.visibility = View.VISIBLE
        } else {
            tvCount.visibility = View.GONE
        }
        ngv.setAdapter(NineGridViewClickAdapter(context, item.images.map {
            ImageInfo().apply {
                thumbnailUrl = it.file_url
                bigImageUrl = it.file_url
            }
        }))
    }
}