package com.example.lifecycle;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class Presenter implements IPresenter {
    private CompositeDisposable compositeDisposable;
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate(LifecycleOwner owner) {
        Log.e(TAG, "onCreate");
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        Log.e(TAG, "onStart");
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        Log.e(TAG, "onResume");
    }

    @Override
    public void onPause(LifecycleOwner owner) {
        Log.e(TAG, "onPause");
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        Log.e(TAG, "onStop");
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        Log.e(TAG, "onCreate");
        clearAllTask();
    }

    protected void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    private void clearAllTask() {
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
