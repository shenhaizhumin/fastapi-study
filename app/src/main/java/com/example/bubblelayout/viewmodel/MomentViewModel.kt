package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.api.body.CommentBody
import com.example.bubblelayout.api.body.MomentBody
import com.example.bubblelayout.api.body.UserBody
import com.example.bubblelayout.base.BaseViewModel
import com.example.bubblelayout.entity.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MomentViewModel : BaseViewModel() {
    val mMomentListLiveData = MutableLiveData<MutableList<MomentEntity>>()
    val mPublishMomentLiveData = MutableLiveData<MomentEntity>()
    val mPublishCommentListLiveData = MutableLiveData<CommentEntity>()
    val collectLiveData = MutableLiveData<CollectEntity>()
    val fileLiveData = MutableLiveData<MutableList<FileEntity>>()
    val userLiveData = MutableLiveData<UserEntity>()
    val mFriendMomentsLiveData = MutableLiveData<FriendMomentData>()

    fun getFriendMoments(id: Long) {
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .friend_moment(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        mFriendMomentsLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = it.message
                })
        )
    }

    fun getMoments(userId: Int) {
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .getMoments().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        mMomentListLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = it.message
                })
        )
    }

    /**
     * 发布朋友圈
     */
    fun publishMoment(body: MomentBody) {
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .publish(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        mPublishMomentLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = it.message
                })
        )
    }

    /**
     * 发布评论
     */
    fun publishComment(body: CommentBody) {
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .publishComment(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        mPublishCommentListLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = it.message
                })
        )
    }

    /**
     * 收藏
     */
    fun collect(momentId: Int) {
        val body = MomentBody()
        body.moment_id = momentId
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .collect(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        collectLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = it.message
                })
        )
    }

    fun uploadFiles(files: List<File>) {
        val fileBodys = files.map {
            val imageBody: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), it)
            return@map MultipartBody.Part.createFormData("file_list", it.name, imageBody)
        }
//        val fileParams = mapOf("file_list" to fileBodys)
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .uploadFiles(fileBodys)
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
}