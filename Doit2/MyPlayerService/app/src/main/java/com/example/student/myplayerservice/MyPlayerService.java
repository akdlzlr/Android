package com.example.student.myplayerservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyPlayerService extends Service {

    MediaPlayer player;     // mp3파일을 재생하는 MediaPlayer 객체 변수
    String filePath;        // mp3파일의 경로를 저장하는 변수

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String btn = intent.getStringExtra("btn");
            String time = intent.getStringExtra("time");

            Intent intent1 = new Intent("com.example.student.myplayerservice");

            if(btn != null) {
                if(btn.equals("play") || btn.equals("pause")) {
                    if(player.isPlaying()) {
                        player.pause();
                        intent1.putExtra("state", "pause");
                    } else {
                        player.start();
                        intent1.putExtra("state", "play");
                    }
                } else if(btn.equals("stop")) {
                    player.stop();
                    try {
                        intent1.putExtra("state", "stop");
                        player.prepare();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }

                if(time != null) {
                    if(time.equals("running_time")) {
                        if(player.isPlaying() && player != null) {
                            intent1.putExtra("cur_time", Integer.toString(player.getCurrentPosition()));
                            intent1.putExtra("full_time", Integer.toString(player.getDuration()));
                        }
                        sendBroadcast(intent1);
                    }
                }

                // 수행한 상태를 기록한 인텐드를 패키지 내에서
                // 브로드 캐스팅 한다.
                sendBroadcast(intent1);
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filePath = intent.getStringExtra("filePath");
        if(filePath != null) {
            try {
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        Intent intent = new Intent("com.example.student.myplayerservice");
                        intent.putExtra("state", "stop");
                        sendBroadcast(intent);
                        stopSelf();
                    }
                });
                player.setDataSource(filePath);
                player.prepare();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        Log.d("MyPlayerService_log", "Service onStartCommend()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // mp3 재생을 위한 MediaPlayer 객체를 생성한다.
        player = new MediaPlayer();
        // 액티비티와 통신을 위한 리시버를 등록한다.
        registerReceiver(receiver, new IntentFilter("com.example.student.myplayerservice"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyPlayerService_log", "Service onDestroy()");
        // 서비스가 종료될때 리시버를 등록 해제한다.
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
