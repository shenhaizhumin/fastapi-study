package com.example.bubblelayout.api.body

import java.io.Serializable

/**
 * content: str = Field(...)
content_url: str = Field(None)
images: List[ImageInSchema] = Field(None)
 */
data class MomentBody(val content: String, val content_url: String?, val images: List<Int>?) :
    Serializable