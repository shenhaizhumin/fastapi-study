package com.example.bubblelayout.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bubblelayout.entity.UserEntity

class FriendListAdapter(layoutId: Int, dataList: MutableList<UserEntity>) :
    BaseQuickAdapter<UserEntity, BaseViewHolder>(layoutId, dataList) {
    override fun convert(holder: BaseViewHolder, item: UserEntity) {

    }
}