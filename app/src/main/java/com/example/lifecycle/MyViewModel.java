package com.example.lifecycle;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MyViewModel extends ViewModel {

    final User user = new User("张三", 22);

    public void onClick(View view) {
        updateUser();
    }

    public void updateUser() {
        user.setName("name:" + new Random().nextInt(20));
        user.setAge(new Random().nextInt(30));
    }
}
