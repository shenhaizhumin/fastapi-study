package com.example.bubblelayout.entity

import java.io.Serializable

/**
 * 评论 实体类
 */
class CommentEntity(
    val id: Int, val content: String, val publish_time: String,
    val moment_id: Int,
    val publisher: PublisherEntity
) : Serializable