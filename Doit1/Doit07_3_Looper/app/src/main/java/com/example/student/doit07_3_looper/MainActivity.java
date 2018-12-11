package com.example.student.doit07_3_looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MainHandler mainHandler;
    ProcessThread thread1;
    EditText et1,et2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainHandler = new MainHandler();

        thread1 = new ProcessThread();

        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        btn1 = (Button)findViewById(R.id.btn1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String instr = et1.getText().toString();
                Message msgToSend = Message.obtain();
                msgToSend.obj = instr;

                thread1.handler.sendMessage(msgToSend);
            }
        });

        thread1.start();
    }

    class ProcessThread extends Thread{
        ProcessHandler handler;

        public ProcessThread(){
            handler = new ProcessHandler();
        }

        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }

    class ProcessHandler extends Handler{
        public void handleMessage(Message msg){
            Message resultMsg = Message.obtain();
            resultMsg.obj = msg.obj + " Mike!!!";

            mainHandler.sendMessage(resultMsg);
        }
    }

    class MainHandler extends Handler{
        @Override
        public void handleMessage(Message msg){
            String str = (String) msg.obj;
            et2.setText(str);
        }
    }
}
