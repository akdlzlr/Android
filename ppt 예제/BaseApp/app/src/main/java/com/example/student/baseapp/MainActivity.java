package com.example.student.baseapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text1;

    Button button1;
    Button button2;

    RadioButton rb1;
    RadioButton rb2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("좀 그럴듯한 앱");
        // 이미지 넣기?
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);


        // 버튼 찾기?
        text1 = (EditText)findViewById(R.id.text1);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        rb1 =(RadioButton)findViewById(R.id.rb1);
        rb2 =(RadioButton)findViewById(R.id.rb2);
        iv =(ImageView)findViewById(R.id.iv);


        // 버튼 이벤트 설정
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(),text1.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });


        // 버튼 2번
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text1.getText().toString()));
                startActivity(intent);
            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.oreo);
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv.setImageResource(R.drawable.kitkat);
            }
        });

    }
}
