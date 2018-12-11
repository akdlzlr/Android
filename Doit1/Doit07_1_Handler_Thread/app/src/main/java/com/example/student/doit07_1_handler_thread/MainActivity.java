package com.example.student.doit07_1_handler_thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressHandler handler;
    ProgressBar bar;
    TextView textView1;
    boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView)findViewById(R.id.textView1);
        bar = (ProgressBar)findViewById(R.id.progress);
        handler = new ProgressHandler();
    }

    @Override
    protected void onStart() {
        super.onStart();

        bar.setProgress(0);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; i++) {
                        Thread.sleep(1000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }
                } catch (Exception ex) {
                    Log.e("MainActivity", "Exception in processing message", ex);
                }
            }
        });
        isRunning = true;
        thread1.start();

    }

    @Override
    protected void onStop() {
        super.onStop();

        isRunning = false;
    }

    public class ProgressHandler extends Handler {
        public void handleMessage(Message msg) {
            bar.incrementProgressBy(5);

            if (bar.getProgress() == bar.getMax()) {
                textView1.setText("Done");
            } else {
                textView1.setText("Working..." + bar.getProgress());
            }
        }
    }
}
