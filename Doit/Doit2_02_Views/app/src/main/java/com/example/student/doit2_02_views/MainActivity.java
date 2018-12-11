package com.example.student.doit2_02_views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 텍스트 뷰의 객체를 만든다.

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃에 뷰 중에서 tv1을 객체로 만든다.
        tv1 = (TextView)findViewById(R.id.tv1);

        // 적혀있는 문자열
        String txt = tv1.getText().toString();

        // 내가 바꾸고 싶은 문자열
        tv1.setText("글자 글자 글자");

        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}
