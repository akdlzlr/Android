package com.example.student.doit_8_2_javasocketclient;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        Log.d("ClientThread", "액티비티 생성");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ClientThread", "버튼 누름");
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread {
        @Override
        public void run() {
            String host = "localhost";
            int port = 5001;
            Log.d("ClientThread", "run 실행");
            try {
                Socket socket = new Socket(host, port);

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject("Hi ");
                outputStream.flush();
                Log.d("ClientThread", "서버로 보냄");


                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());

                final Object input = instream.readObject();
                Log.d("ClientThread", "받은 데이터 " + input);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("받은 데이터 : " + input);
                    }
                });


            } catch (Exception e) {
                Log.d("ClientThread", "오류발생");
            }

        }
    }
}
