package com.example.bubblelayout.api.body

import java.io.Serializable

data class UserBody(
    val username: String?,
    val password: String?,
    val email: String?,
    val mobile: String?,
    val nickname: String?,
    val avatar_url: String?
) : Serializable