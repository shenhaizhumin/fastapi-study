package com.example.bubblelayout.entity

data class P2PMessageEntity(
    val friend_user_id: Int,
    val msg: String,
    val latest_msg: String,
    val to_id: Int,
    val friend_nickname: String,
    val friend_avatar_url: String,
    val latest_time: String
)