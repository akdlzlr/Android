package com.example.student.doit10_3_audiorecorder;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static final String RECORDED_FILE = "/sdcard/recorded2.mp4";

    Button startR, stopR, startP, stopP;
    MediaPlayer player;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO}, Activity.MODE_PRIVATE);

        startR = (Button) findViewById(R.id.btn1);
        stopR = (Button) findViewById(R.id.btn2);
        startP = (Button) findViewById(R.id.btn3);
        stopP = (Button) findViewById(R.id.btn4);

        startR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recorder != null) {
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }

                recorder = new MediaRecorder();

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILE);

                try {
                    recorder.prepare();
                    recorder.start();
                    Toast.makeText(getApplicationContext(), "녹음 시작", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("SampleAudioRecorder", "Exception : ", e);
                }
            }
        });

        stopR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recorder == null) {
                    return;
                }

                recorder.stop();
                recorder.release();
                recorder = null;

                ContentValues values = new ContentValues(10);

                values.put(MediaStore.MediaColumns.TITLE, "Recorded");
                values.put(MediaStore.Audio.Media.ALBUM, "Audio Album");
                values.put(MediaStore.Audio.Media.ARTIST, "Mike");
                values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Audio");
                values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
                values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
                values.put(MediaStore.MediaColumns.DATE_ADDED,
                        System.currentTimeMillis() / 1000);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp4");
                values.put(MediaStore.Audio.Media.DATA, RECORDED_FILE);

                Uri audioUri = getContentResolver().insert(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, values);
                if (audioUri == null) {
                    Log.d("SampleAudioRecorder", "Audio insert failed.");
                    return;
                }
                Toast.makeText(getApplicationContext(), "녹음 종료", Toast.LENGTH_SHORT).show();
            }
        });

        startP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player != null) {
                    player.stop();
                    player.release();
                    player = null;
                }

                Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다.",
                        Toast.LENGTH_LONG).show();
                try {
                    player = new MediaPlayer();

                    player.setDataSource(RECORDED_FILE);
                    player.prepare();
                    player.start();
                } catch (Exception e) {
                    Log.e("SampleAudioRecorder", "Audio play failed.", e);
                }
            }
        });

        stopP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (player == null)
                    return;

                Toast.makeText(getApplicationContext(), "재생이 중지되었습니다.",
                        Toast.LENGTH_LONG).show();

                player.stop();
                player.release();
                player = null;
            }
        });
    }


    protected void onPause() {
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
        super.onPause();
    }

    protected void onResume(){
        super.onResume();
        recorder = new MediaRecorder();
    }

}
