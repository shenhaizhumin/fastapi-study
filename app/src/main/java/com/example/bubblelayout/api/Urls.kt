package com.example.bubblelayout.api

object Urls {
    val BASE_URL = "http://39.107.77.70"

    const val session = "/login"
    const val nodes =
        "/api/client/nodes?space_id=1&is_root=1&offset=0&limit=20&parent_uid=65738d425efa73d6b94d9994658b5a405a9c91e5"

    const val categoryApi = "/category/{category}/type/{type}/page/{page}/count/{count}"

    const val categories = "/categories/{categories}"

    const val register = "/register"
    const val userInfo = "/userInfo"
    const val updateUser = "/updateUser"
    const val uploadFile = "/uploadFile"
    const val moments = "/moments"
    const val publish = "/moments/publish"
    const val publishComment = "/moments/publishComment"
    const val collect = "/moments/collect"
}