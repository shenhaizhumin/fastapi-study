package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {

    val dataListLiveData = MutableLiveData<Any>()

    fun getNodes(){
        compositeDisposable.add(
            RetrofitManager.getInstance().createService(ApiService::class.java)
                .node()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        dataListLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = "${it.message}"
                })
        )
    }

}