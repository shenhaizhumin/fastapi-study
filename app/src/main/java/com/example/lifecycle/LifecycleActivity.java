package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.SystemClock;

import com.example.lifecycle.databinding.ActivityLifecycleBinding;

public class LifecycleActivity extends AppCompatActivity {

    ActivityLifecycleBinding activityLifecycleBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLifecycleBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle);
        activityLifecycleBinding.setLifecycleOwner(this);
        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        activityLifecycleBinding.setViewModel(myViewModel);
//        dataBinding
        activityLifecycleBinding.setUser(myViewModel.user);


    }
}
