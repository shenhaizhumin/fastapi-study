package com.example.bubblelayout.annotation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bubblelayout.R;

public class AnnotationTestActivity extends AppCompatActivity {

    @BindView(id = R.id.tv)
    private TextView textView;
    @BindView(id = R.id.tv2)
    private TextView textView2;
    @BindView(id = R.id.tv3)
    private TextView textView3;
    @BindView(id = R.id.tv4)
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_test);
        ViewUtils.getInstance().inject(this);
        textView.setText("2222652636236232");
        textView2.setText("2222652636236232");
        textView3.setText("2222652636236232");
        textView4.setText("2222652636236232");
    }

    @BindClick(id = R.id.tv3)
    private void click(View view) {
        Toast.makeText(this, "hahahah", 1).show();
    }
}
