package com.example.student.doit06_1_customviewdrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 직접 만든 뷰를 화면에 설정
        CustomViewDrawable myView = new CustomViewDrawable(this);
        setContentView(myView);
    }
}
