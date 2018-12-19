package com.example.student.mytimer;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText et1, et2;
    Button btnStart, btnStop, btn30, btn30m, btn10, btnReset;
    ProgressBar pb1;
    String disMin;
    String disSec;

    int min = 0, sec = 0;
    float pbPercent=0;
    Thread my_thread = null;
    Boolean timerRun = false;
    String pMin,pSec;
    float maxTime, restTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btn30 = (Button) findViewById(R.id.btn30);
        btn30m = (Button) findViewById(R.id.btn30m);
        btn10 = (Button) findViewById(R.id.btn10);
        btnReset = (Button) findViewById(R.id.btnReset);
        pb1 = (ProgressBar) findViewById(R.id.pb1);

        btn30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sec += 30;
                if (sec >= 60) {
                    sec = sec - 60;
                    min++;
                }
                et1.setText(min + "");
                et2.setText(sec + "");
            }
        });
        btn30m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min+=30;
                et1.setText(min + "");
                et2.setText(sec + "");
            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min+=10;
                et1.setText(min + "");
                et2.setText(sec + "");
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                min = 0;
                sec = 0;
                et1.setText(min + "");
                et2.setText(sec + "");
                tv1.setText("00:00");
                pb1.setProgress(0);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timerRun==false) {
                    MyTimerThread runnable = new MyTimerThread();
                    my_thread = new Thread(runnable);
                    my_thread.start();
                    timerRun = true;
                    pMin = et1.getText().toString();
                    pSec = et2.getText().toString();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_thread.interrupt();
                timerRun = false;
                et1.setText(min + "");
                et2.setText(sec + "");
            }
        });
    }

    Handler handler = new Handler();

    class UIUpdate implements Runnable {
        @Override
        public void run() {

            tv1.setText(disMin + ":" + disSec);

            maxTime = Integer.parseInt(pMin)*60+Integer.parseInt(pSec);
            restTime = Integer.parseInt(disMin)*60+Integer.parseInt(disSec);


            pbPercent = ((maxTime-restTime)/maxTime)*100;

            Log.d("maxTime", maxTime+"");
            Log.d("restTime", restTime+"");
            Log.d("pbPercent", pbPercent+"");

            pb1.setProgress((int)pbPercent);
        }
    }

    class MyTimerThread implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (min < 10) {
                        disMin = "0" + min;
                    } else {
                        disMin = min + "";
                    }

                    if (sec < 10) {
                        disSec = "0" + sec;
                    } else {
                        disSec = "" + sec;
                    }

                    handler.post(new UIUpdate());

                    sec--;

                    if (min > 0 && sec < 0) {
                        min--;
                        sec = 59;
                    }
                    if (min == 0 && sec < 0) {
                        my_thread.interrupt();
                        timerRun=false;
                    }
                    SystemClock.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
