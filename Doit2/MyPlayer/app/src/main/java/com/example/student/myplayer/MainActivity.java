package com.example.student.myplayer;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean bReadPerm = false;
    boolean bWritePerm = false;
    Button button_prev, button_next;
    int num = 0;
    Intent ServiceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermission();

        button_prev = (Button) findViewById(R.id.button_prev);
        button_next = (Button) findViewById(R.id.button_next);
        button_prev.setOnClickListener(new MyButtonListener());
        button_next.setOnClickListener(new MyButtonListener());

        if(bReadPerm){
            String state = Environment.getExternalStorageState();

            if(state.equals(Environment.MEDIA_MOUNTED)){
                try {
                    String musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/music.mp3";
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra("file",musicPath);
                    startService(intent);
                } catch (Exception e) {
                    Log.d("PlayMp3", "mp3 file error");
                }
            }
        }
    }

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_prev:
                    num--;
                    if (num < 0) {
                        num = 3;
                    }
                    ServiceIntent = new Intent(MainActivity.this, MyService.class);
                    ServiceIntent.putExtra("num",num);
                    startService(ServiceIntent);
                    break;
                case R.id.button_next:
                    num++;
                    if (num > 3) {
                        num = 0;
                    }
                    ServiceIntent = new Intent(MainActivity.this, MyService.class);
                    ServiceIntent.putExtra("num",num);
                    startService(ServiceIntent);
                    break;
            }
        }
    }

    private void setPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            bReadPerm = true;
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            bWritePerm = true;
        }

        if (!bReadPerm && !bWritePerm) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]
            permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                bWritePerm = true;
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
