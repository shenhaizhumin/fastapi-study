package com.example.bubblelayout.utils

import com.blankj.utilcode.util.SPUtils

object UserInfoUtil {

    fun getUserId(): Long {
        return SPUtils.getInstance().getLong("user_id")
    }

    fun setUserId(userId: Long) {
        SPUtils.getInstance().put("user_id", userId)
    }

//    fun setCurrentSpaceId(){}

    fun setAccessToken(token: String) {
        SPUtils.getInstance().put("access-token", token)
    }

    fun getAccessToken(): String {
        return SPUtils.getInstance().getString("access-token")
    }
}