package com.example.student.chapter14_test4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;
import java.util.ArrayList;

public class MusicService extends Service {

    MediaPlayer mp = new MediaPlayer();
    int i = 0;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        android.util.Log.i("서비스 테스트", "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("서비스 테스트", "onDestroy");
        mp.stop();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("서비스 테스트", "onStartCommand");

        final String mp3Path = intent.getStringExtra("path");
        final ArrayList mp3List = intent.getStringArrayListExtra("fileName");
        android.util.Log.i("list", mp3List.toString());


        String selectedMP3 = (String) mp3List.get(i);
        try {
            String str = (mp3Path + selectedMP3).trim();
            android.util.Log.i("str", str);
            mp.setDataSource(str);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
        }

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mp.stop();
                mp.reset();
                try {
                    if (i < mp3List.size()) {
                        i++;
                    }else {
                        i=0;
                    }
                    String str2 = mp3Path + mp3List.get(i);
                    mp.setDataSource(str2);
                    mp.prepare();
                    mp.start();
                } catch(IOException e){

                    }

                }
            }
        );

        return super.onStartCommand(intent, flags, startId);
    }
}
