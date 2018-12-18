package com.example.student.myplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    MediaPlayer player;
    String[] musicList = {"song1.mp3", "music1.mp3", "music2.mp3", "music3.mp3"};
    int num = 0;
    String musicPath;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate() 실행");

        player = new MediaPlayer();
        musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/"+musicList[num];

        try{
            player.setDataSource(musicPath);
            player.prepare();
        }catch (Exception e){}

        player.start();
        Log.d("MyService", "player.start() 실행");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("MyService", "onStartCommand() 실행");

        player.stop();
        player.reset();

        num = intent.getIntExtra("num",0);

        musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/"+musicList[num];
        Log.d("MyService -> Path : ", musicPath);
        try{
            player.setDataSource(musicPath);
            player.prepare();
        }catch (Exception e){}
        player.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MyService", "onDestroy() 실행");
        super.onDestroy();
    }
}
