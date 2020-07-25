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

    fun generateObjectName(senderId:Int,receiveId:Int): String {
        return "$senderId$receiveId"
    }

    /**
     * 1,2,3,4
     * 1,2 || 3,4
     * 2,1||3,4
     * 1,2||4,3
     * 2,1||4,3
     */

    fun eqObjectName(senderId:Int,receiveId:Int,localSid:Int,localRid:Int): Boolean {
        return "$senderId$receiveId"=="$localSid$localRid" ||"$receiveId$senderId"=="$localSid$localRid" || "$senderId$receiveId"=="$localRid$localSid"|| "$receiveId$senderId"=="$localRid$localSid"
    }
}