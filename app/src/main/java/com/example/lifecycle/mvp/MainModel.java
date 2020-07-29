package com.example.lifecycle.mvp;

public class MainModel {

    public void getData(int page, Callback callback) {
        if (page % 2 == 0) {
            callback.onData("page 0");
        } else {
            callback.onEmpty("msg empty");
        }
    }

    interface Callback {
        void onData(String data);

        void onEmpty(String msg);
    }
}
