package com.example.student.myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    BroadcastReceiver screenOn;
    BroadcastReceiver screenOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyReceiverTest.class);
                intent.putExtra("msg", "hello");
                sendBroadcast(intent);
            }
        });


        screenOn = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Screen_State", "screen on");
            }
        };

        screenOff = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Screen_State", "screen off");
            }
        };

        registerReceiver(screenOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(screenOff, new IntentFilter(Intent.ACTION_SCREEN_OFF));


    }
}
