package com.example.student.doit_8_2_javasocketserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ServerThread", "서버가 생성 준비됨");
        Button button = (Button) findViewById(R.id.btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServerThread thread = new ServerThread();

                thread.start();
            }
        });

    }

    class ServerThread extends Thread {
        @Override
        public void run() {

            int port = 5001;

            try {
                ServerSocket server = new ServerSocket(port);
                Log.d("ServerThread", "서버가 실행됨");
                while (true) {
                    Socket socket = server.accept();

                    ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());

                    Object input = instream.readObject();
                    Log.d("ServerThread", "input : " + input);

                    ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                    outstream.writeObject(input + "from server");
                    outstream.flush();
                    Log.d("ServerThread", "output 보냄");
                    socket.close();
                }

            } catch (Exception e) {

            }
        }
    }
}
