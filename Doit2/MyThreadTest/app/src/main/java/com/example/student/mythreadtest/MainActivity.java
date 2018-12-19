package com.example.student.mythreadtest;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    TextView tv1;
    ProgressBar pb1;
    int value = 0;
    Thread my_thread = null;
    Boolean runThread = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        tv1 = (TextView)findViewById(R.id.tv1);
        pb1 = (ProgressBar)findViewById(R.id.pb1);

        btnStart.setOnClickListener(new BtnLitener());
        btnStop.setOnClickListener(new BtnLitener());

    }

    Handler handler = new Handler();

    class UIUpdate implements Runnable{
        @Override
        public void run() {
            if(value<1000){
                tv1.setText(Integer.toString(value));
                pb1.setProgress(value/10);
            }else{
                tv1.setText("1000번을 카운트 하였습니다.");
                pb1.setProgress(value/10);
            }
        }
    }

    class MyThread implements Runnable{
        @Override
        public void run() {
            try{
                while(runThread) {
                    if(value < 1000) {
                        SystemClock.sleep(1000);
                        value++;
                        handler.post(new UIUpdate());
                    }

                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    class BtnLitener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnStart:
                    if(my_thread==null){
                        runThread=true;
                        MyThread runnable = new MyThread();
                        my_thread = new Thread(runnable);
                        my_thread.start();
                    }
                    break;
                case R.id.btnStop:
                    if(my_thread!=null){
                        runThread=false;
                        tv1.setText("사용자에 의해 종료 되었습니다.");
                        pb1.setProgress(0);
                    }
                    break;
            }
        }
    }
}
