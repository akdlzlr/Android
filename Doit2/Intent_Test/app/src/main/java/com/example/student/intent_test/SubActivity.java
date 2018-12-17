package com.example.student.intent_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    TextView textView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView)findViewById(R.id.tv1);
        btn = (Button)findViewById(R.id.btnFinish);

        Intent intent = getIntent();

        if(intent!=null){
            String phoneNumber = intent.getStringExtra("phoneNumber");
            textView.setText("전달된 전화번호 : "+phoneNumber);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
