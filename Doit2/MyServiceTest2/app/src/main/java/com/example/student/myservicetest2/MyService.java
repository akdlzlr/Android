package com.example.student.myservicetest2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
            Intent intent_to_activity = new Intent("com.example.student.myservicetest2");

            if(mode !=null){
                if(mode.equals("send1")){
                    Log.d("myService_receive", "send1");
                    intent_to_activity.putExtra("res","response from send1");
                }else if(mode.equals("send2")){
                    Log.d("myService_receive", "send2");
                    intent_to_activity.putExtra("res","response from send2");
                }
                sendBroadcast(intent_to_activity);
            }
        }
    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver, new IntentFilter("com.example.student.myservicetest2"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
