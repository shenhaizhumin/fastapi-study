package com.example.bubblelayout.utils

import com.blankj.utilcode.util.SPUtils

object UserInfoUtil {

    fun getUserId(): Int {
        return SPUtils.getInstance().getInt("user_id")
    }

    fun setUserId(userId: Int) {
        SPUtils.getInstance().put("user_id", userId)
    }

//    fun setCurrentSpaceId(){}

    fun setAccessToken(token: String) {
        SPUtils.getInstance().put("access-token", token)
    }

    fun getAccessToken(): String {
        return SPUtils.getInstance().getString("access-token")
    }

    fun setUserAvatar(user_avatar: String?) {
        SPUtils.getInstance().put("user_avatar", user_avatar)
    }

    fun getUserAvatar(): String? {
        return SPUtils.getInstance().getString("user_avatar")
    }

    fun setUserNickname(nickname: String?) {
        SPUtils.getInstance().put("nickname", nickname)
    }

    fun getUserNickname(): String? {
        return SPUtils.getInstance().getString("nickname")
    }

    fun setMomentImage(momentImage: String?) {
        SPUtils.getInstance().put("momentImage", momentImage)
    }

    fun getMomentImage(): String? {
        return SPUtils.getInstance().getString("momentImage")
    }
}