package com.example.student.chapter6_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Button preBtn,nextBtn;
    ViewFlipper vf1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("자동 넘김 프로그램");

        preBtn = (Button)findViewById(R.id.preBtn);
        nextBtn = (Button)findViewById(R.id.nextBtn);
        vf1 = (ViewFlipper)findViewById(R.id.vf1);


        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               vf1.setFlipInterval(500);
               vf1.startFlipping();
                Toast.makeText(getApplicationContext(),"자동넘김을 시작합니다.",Toast.LENGTH_SHORT).show();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"자동넘김을 중지합니다.",Toast.LENGTH_SHORT).show();
                vf1.stopFlipping();
            }
        });
    }
}
