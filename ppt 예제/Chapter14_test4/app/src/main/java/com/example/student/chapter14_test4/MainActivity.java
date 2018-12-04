package com.example.student.chapter14_test4;

import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    ArrayList<String> mp3List;


    String mp3Path = "/sdcard/Music/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);


        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);


        final Intent intent = new Intent(this, MusicService.class);


        mp3List = new ArrayList<String>();

        File[] list = new File(mp3Path).listFiles();

        String fileName, extName;

        for (File file : list){
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals((String)"mp3")){
                mp3List.add(fileName);
            }
        }
        android.util.Log.i("path", mp3Path);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.util.Log.i("서비스 테스트", "startService()");

                intent.putExtra("path", mp3Path);

                intent.putStringArrayListExtra("fileName",mp3List);
                startService(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.util.Log.i("서비스 테스트", "stopService()");
                stopService(intent);
            }
        });
    }
}
