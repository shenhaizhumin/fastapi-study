package com.example.bubblelayout.entity

data class FileEntity(val id: Int, val file_url: String) {
    override fun toString(): String {
        return "FileEntity(id=$id, file_url='$file_url')"
    }
}

data class CollectEntity(
    val id: Int,
    val create_time: String,
    val moment_id: Int,
    val publisher: PublisherEntity
)