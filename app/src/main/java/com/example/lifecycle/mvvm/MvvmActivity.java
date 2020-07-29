package com.example.lifecycle.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifecycle.R;

public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.liveData.observe(this, s -> Toast.makeText(this, s, Toast.LENGTH_SHORT).show());
        TextView textView = findViewById(R.id.tv);
        textView.setOnClickListener(v -> viewModel.getData());
    }
}
