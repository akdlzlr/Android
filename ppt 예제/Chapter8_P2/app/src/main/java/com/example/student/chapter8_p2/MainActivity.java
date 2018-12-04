package com.example.student.chapter8_p2;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button prev, next;
    myPictureView myPictureView1;
    int curNum = 0;
    File[] imageFiles;
    String imageName;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");

        prev = (Button) findViewById(R.id.prev);
        next = (Button) findViewById(R.id.next);
        myPictureView1 = (myPictureView) findViewById(R.id.myPictureView1);
        tv1 = (TextView) findViewById(R.id.tv1);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        imageFiles = new File(Environment.
                getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles();
        imageName = imageFiles[0].toString();
        myPictureView1.imagePath = imageName;
        tv1.setText((curNum+1)+"/5");


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum <= 0) {
                    curNum = 4;
                }else {
                    curNum--;
                }
                tv1.setText((curNum+1)+"/5");
                imageName = imageFiles[curNum].toString();
                myPictureView1.imagePath = imageName;
                myPictureView1.invalidate();

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum >= imageFiles.length - 1) {
                    curNum = 0;
                } else {
                    curNum++;
                }
                tv1.setText((curNum+1)+"/5");
                imageName = imageFiles[curNum].toString();
                myPictureView1.imagePath = imageName;
                myPictureView1.invalidate();

            }
        });

    }
}
