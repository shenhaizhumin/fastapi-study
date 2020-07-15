package com.example.bubblelayout.api.body

data class UserBody(
    val username: String?,
    val password: String?,
    val email: String?,
    val mobile: String?,
    val nickname: String?,
    val avatar_url: String?
)