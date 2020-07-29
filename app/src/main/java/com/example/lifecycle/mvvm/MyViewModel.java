package com.example.lifecycle.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MyViewModel extends ViewModel {
    public MutableLiveData<String> liveData = new MutableLiveData<>();

    public void getData() {
        if (new Random().nextInt(10) % 2 == 0) {
            liveData.setValue("success");
        } else {
            liveData.setValue("failed");
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
