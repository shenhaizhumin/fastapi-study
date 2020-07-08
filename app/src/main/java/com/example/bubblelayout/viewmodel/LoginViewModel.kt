package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.BaseResponse
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.base.BaseViewModel
import com.example.bubblelayout.entity.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel : BaseViewModel() {


    val userLiveData = MutableLiveData<UserEntity>()

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
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                })
        )
    }

}