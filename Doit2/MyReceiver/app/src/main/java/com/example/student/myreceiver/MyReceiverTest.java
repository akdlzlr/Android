package com.example.student.myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiverTest extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String msg = intent.getStringExtra("msg") + "\nI am BroadcastReceiver";
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
