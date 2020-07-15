package com.example.bubblelayout.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.bubblelayout.api.ApiService
import com.example.bubblelayout.api.RetrofitManager
import com.example.bubblelayout.base.BaseViewModel
import com.example.bubblelayout.entity.CategoryEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiscoverViewModel : BaseViewModel() {

    val categoryLiveData = MutableLiveData<MutableList<CategoryEntity>>()
    val categoriesLiveData = MutableLiveData<MutableList<CategoryEntity>>()

    //category=Path(...), type=Path(...), page=Path(...), count=Path(...)
    fun category(category: String, type: String, page: Int, count: Int) {
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .categoryApi(category, type, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        categoryLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = it.message
                })
        )
    }

    fun categories(category: String){
        compositeDisposable.add(
            RetrofitManager.getInstance()
                .createService(ApiService::class.java)
                .categories(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.code == 200) {
                        categoriesLiveData.value = it.data
                    } else {
                        errorLiveData.value = it.message
                    }
                }, {
                    errorLiveData.value = it.message
                })
        )
    }
}