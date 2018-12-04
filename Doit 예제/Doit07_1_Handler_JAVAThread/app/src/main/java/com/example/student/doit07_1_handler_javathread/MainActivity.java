package com.example.student.doit07_1_handler_javathread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    Button btn1;
    boolean running;
    int value=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView1.setText("스레드에서 받은 값 : "+ value);
            }
        });
    }

    protected void onResume(){
        super.onResume();
        Log.i("SampleJavaThread","실행");
        running = true;

        Thread thread1 = new BackgroundThread();
        thread1.start();
    }

    protected  void onPause(){
        super.onPause();
        Log.i("SampleJavaThread","중지");
        running = false;
        value = 0;

    }


    class BackgroundThread extends Thread{
        public void run(){
            while(running){
                try{
                    Thread.sleep(1000);
                    value++;
                }catch (InterruptedException ex){
                    Log.e("SampleJavaThread", "Exception in thread.", ex);
                }
            }
        }
    }
}
