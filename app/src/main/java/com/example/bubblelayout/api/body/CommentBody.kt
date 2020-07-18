package com.example.bubblelayout.api.body

import java.io.Serializable

/**
 * moment_id: int = Field(...)
content: str = Field(..., max_length=200)
 */
data class CommentBody(val moment_id: Int, val content: String) : Serializable