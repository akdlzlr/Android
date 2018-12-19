package com.example.student.myplayerservice;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean bReadPerm = false;          //SD카드를 읽기 위한 여부를 저장
    Button btnPlay, btnStop;            // 버튼 객체를 위한 변수
    boolean bStatePlay = false;         // 재생 상태 유무를 위한 변수,
    // true : 재생, false : 정지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // sd 카드 읽기를 위한 권한을 처리하는 함수
        setPermission();

        // 버튼 객체를 생성
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);

        // 버튼의 리스너 등록
        btnPlay.setOnClickListener(new myButtonListener());
        btnStop.setOnClickListener(new myButtonListener());

        // 서비스와 통신을 위해 리시버를 등록
        registerReceiver(receiver, new IntentFilter("rj.myplayerservice"));

        if (bReadPerm) {
            // SD 카드의 상태를 확인
            String state = Environment.getExternalStorageState();

            if (state.equals(Environment.MEDIA_MOUNTED)) {
                // SD카드가 장착이 되어 있다면
                try {
                    // SD 카드 안에 있는 mp3 파일의 경로를 읽어온다.
                    String musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/Music3.mp3";
                    // 인텐트에 mp3 파일 경로를 저장한다.
                    Intent intent = new Intent(MainActivity.this, MyPlayerService.class);
                    intent.putExtra("filePath", musicPath);
                    // 서비스를 시작한다.
                    startService(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra("state");

            if (state != null) {
                if (state.equals("play")) {
                    bStatePlay = true;
                    btnPlay.setText("Pause");
                } else if (state.equals("pause") || state.equals("stop")) {
                    bStatePlay = false;
                    btnPlay.setText("Play");
                }
            }
        }
    };

    class myButtonListener implements View.OnClickListener {
        Intent intent;

        @Override
        public void onClick(View view) {
            intent = new Intent("com.example.student.myplayerservice");
            switch (view.getId()) {
                case R.id.btnPlay:
                    if (bStatePlay) {
                        intent.putExtra("btn", "pause");
                    } else {
                        intent.putExtra("btn", "play");
                    }
                    break;
                case R.id.btnStop:
                    intent.putExtra("btn", "stop");
                    break;
            }
            sendBroadcast(intent);
        }
    }

    private void setPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            bReadPerm = true;
        }


        if (!bReadPerm) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }
        }
    }
}
