package com.example.bubblelayout.entity

data class FriendMomentData(
    val list: List<UserMomentEntity>,
    val user: FriendInfo
)

data class FriendInfo(
    val nickname: String,
    val moment_image: String,
    val avatar_url: String
)

data class UserMomentEntity(
    val day_key: String,
    val moments: List<MomentEntity>,
    val year: Any
)

//data class Moment(
//    val collects: List<Collect>,
//    val comments: List<Comment>,
//    val content: String,
//    val content_url: Any,
//    val id: Int,
//    val images: List<Any>,
//    val publish_time: String,
//    val publisher: Publisher,
//    val release_time: String
//)
//
//data class Collect(
//    val create_time: String,
//    val id: Int,
//    val moment_id: Int,
//    val publisher: Publisher
//)
//
//data class Comment(
//    val content: String,
//    val id: Int,
//    val moment_id: Int,
//    val publish_time: String,
//    val publisher: Publisher
//)
//
//data class Publisher(
//    val avatar_url: Any,
//    val id: Int,
//    val nickname: String
//)

//data class PublisherX(
//    val avatar_url: Any,
//    val id: Int,
//    val nickname: String
//)
//
//data class PublisherXX(
//    val avatar_url: Any,
//    val id: Int,
//    val nickname: String
//)
//
//data class User(
//    val avatar_url: Any,
//    val moment_image: String,
//    val nickname: String
//)