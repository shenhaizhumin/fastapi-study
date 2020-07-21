package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.base.BaseViewModel
import com.example.bubblelayout.entity.ChatMessageEntity
import com.example.bubblelayout.entity.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ChatViewModel : BaseViewModel() {

    val chatListLiveData = MutableLiveData<ChatMessageEntity>()

    val friendListLiveData = MutableLiveData<MutableList<UserEntity>>()
    fun getFriendList() {
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .allUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        friendListLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                    it.printStackTrace()
                })
        )
    }



}