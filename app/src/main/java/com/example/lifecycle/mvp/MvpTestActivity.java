package com.example.lifecycle.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lifecycle.R;

public class MvpTestActivity extends AppCompatActivity implements MainContract.IView {

    private MainPresenter mainPresenter;
    private TextView textView;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_test);
        mainPresenter = new MainPresenter(this);
        textView = findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.getData(page);
                page++;
            }
        });
    }

    @Override
    public void onSuccess(String data) {
        textView.setText(data);
    }

    @Override
    public void onFailed(String msg) {
        textView.setText(msg);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
