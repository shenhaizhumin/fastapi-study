package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.base.BaseViewModel
import com.example.bubblelayout.entity.FileEntity
import com.example.bubblelayout.entity.UserEntity
import com.example.bubblelayout.utils.UserInfoUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.*


class LoginViewModel : BaseViewModel() {


    val userLiveData = MutableLiveData<UserEntity>()
    val fileLiveData = MutableLiveData<FileEntity>()

    fun login(username: String, password: String) {
        val params = mapOf("username" to username, "password" to password)
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .session(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        userLiveData.value = it.data
                        UserInfoUtil.setUserId(it.data.id)
                        UserInfoUtil.setAccessToken("Bearer ${it.data.access_token}")
                        UserInfoUtil.setUserAvatar(it.data.avatar_url)
                        UserInfoUtil.setUserNickname(it.data.nickname)
                        UserInfoUtil.setMomentImage(it.data.moment_image)
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                    it.printStackTrace()
                })
        )
    }

    fun register(body: UserBody) {
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .register(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        userLiveData.value = it.data
                        UserInfoUtil.setUserId(it.data.id)
                        UserInfoUtil.setAccessToken("Bearer ${it.data.access_token}")
                        UserInfoUtil.setUserAvatar(it.data.avatar_url)
                        UserInfoUtil.setUserNickname(it.data.nickname)
                        UserInfoUtil.setMomentImage(it.data.moment_image)
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                })
        )
    }

    fun getUserInfo() {
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .userInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        userLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                })
        )
    }

    fun updateUser(body: UserBody) {
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .updateUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        userLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                })
        )
    }

    fun uploadFile(file: File) {
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "image_file", file.name,
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            )
            .build()
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .uploadFile(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        fileLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = "${it.message}"
                })
        )
    }


    @Throws(IOException::class)
    fun upload(url: String?, file: File): ResponseBody {
        val client = OkHttpClient()
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "image_file", file.name,
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            )
            .build()
        val request: Request = Request.Builder()
            .header("Authorization", "ClientID" + UUID.randomUUID())
            .url(url)
            .post(requestBody)
            .build()
        val response: Response = client.newCall(request).execute()
        if (!response.isSuccessful) throw IOException("Unexpected code $response")
        return response.body()
    }

}