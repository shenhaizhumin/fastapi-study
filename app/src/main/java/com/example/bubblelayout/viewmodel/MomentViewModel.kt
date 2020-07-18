package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.api.body.CommentBody
import com.example.bubblelayout.api.body.MomentBody
import com.example.bubblelayout.base.BaseViewModel
import com.example.bubblelayout.entity.CommentEntity
import com.example.bubblelayout.entity.MomentEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MomentViewModel : BaseViewModel() {
    val mMomentListLiveData = MutableLiveData<MutableList<MomentEntity>>()
    val mPublishMomentListLiveData = MutableLiveData<MomentEntity>()
    val mPublishCommentListLiveData = MutableLiveData<CommentEntity>()

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

    fun publishMoment(body: MomentBody) {
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .publish(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        mPublishMomentListLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    it.printStackTrace()
                    errorLiveData.value = it.message
                })
        )
    }

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
}