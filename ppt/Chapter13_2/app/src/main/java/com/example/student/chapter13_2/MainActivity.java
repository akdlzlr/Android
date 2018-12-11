package com.example.student.chapter13_2;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar, progressBar2;
    Button button, button2;
    //SeekBar seekBar;
    TextView tv, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        progressBar2 = (ProgressBar) findViewById(R.id.seekBar1);
        tv = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread() {
                    @Override
                    public void run() {
                        for (int i = progressBar2.getProgress(); i < 60; i++) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar2.setProgress(progressBar2.getProgress() + 2);
                                    tv2.setText("1번 진행률 : " + progressBar2.getProgress() + "%");
                                }
                            });

                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread() {
                    @Override
                    public void run() {
                        for (int i = progressBar.getProgress(); i < 100; i++) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressBar.getProgress() + 1);
                                    tv3.setText("2번 진행률 : " + progressBar.getProgress() + "%");
                                }
                            });
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar2.setProgress(10);
                progressBar.setProgress(30);
                tv2.setText("1번 진행률 : 10%");
                tv3.setText("2번 진행률 : 30%");
            }
        });

        /*seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tv.setText("진행률 : "+ i +"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

*/


    }
}
