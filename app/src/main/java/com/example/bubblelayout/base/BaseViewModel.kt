package com.example.bubblelayout.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    val TAG = javaClass.simpleName
    val compositeDisposable = CompositeDisposable()
    val errorLiveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "onCleared")
        compositeDisposable.clear()
    }
}