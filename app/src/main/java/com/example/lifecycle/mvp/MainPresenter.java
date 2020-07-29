package com.example.lifecycle.mvp;

public class MainPresenter implements MainContract.IPresenter {

    private MainModel mainModel = new MainModel();
    private MainContract.IView view;

    MainPresenter(MainContract.IView view) {
        this.view = view;
    }

    @Override
    public void getData(int page) {
        mainModel.getData(page, new MainModel.Callback() {
            @Override
            public void onData(String data) {
                view.onSuccess(data);
            }

            @Override
            public void onEmpty(String msg) {
                view.onFailed(msg);
                view.showToast(msg);
            }
        });
    }

}
