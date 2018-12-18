package com.example.student.myservicetest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop, btnSend1, btnSend2;
    TextView tv1;
    BroadcastReceiver receiver_form_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnSend1 = (Button)findViewById(R.id.btnSend1);
        btnSend2 = (Button)findViewById(R.id.btnSend2);
        tv1 = (TextView)findViewById(R.id.tv1);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });

        btnSend1.setOnClickListener(new myButtonListener());
        btnSend2.setOnClickListener(new myButtonListener());

        receiver_form_service = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String res = intent.getStringExtra("res");

                if(res!=null){
                    tv1.setText(res);
                }
            }
        };
        registerReceiver(receiver_form_service,new IntentFilter("com.example.student.myservicetest2"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver_form_service);
    }

    class myButtonListener implements View.OnClickListener{
        Intent intent;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnSend1:
                    intent = new Intent("com.example.student.myservicetest2");
                    intent.putExtra("mode","send1");
                    break;
                case R.id.btnSend2:
                    intent = new Intent("com.example.student.myservicetest2");
                    intent.putExtra("mode", "send2");
                    break;
            }
            sendBroadcast(intent);
        }
    }
}
