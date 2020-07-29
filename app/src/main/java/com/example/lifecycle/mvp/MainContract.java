package com.example.lifecycle.mvp;

public interface MainContract {

    interface IView extends com.example.lifecycle.mvp.IView {
        void onSuccess(String data);

        void onFailed(String msg);
    }

    interface IPresenter extends com.example.lifecycle.mvp.IPresenter {
        void getData(int page);
    }
}
