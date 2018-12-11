package com.example.student.chapter8_1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnR, btnW;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnR = (Button)findViewById(R.id.btnR);
        btnW = (Button)findViewById(R.id.btnW);
        tv1 = (TextView)findViewById(R.id.tv1);
        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fos = openFileOutput("file.txt",
                            Context.MODE_WORLD_WRITEABLE);
                    // file.txt로 파일을 쓰기모드로 연다.
                    // 경로는 /data/data/패키지명/file/file.txt
                    // 파일 모드에 쓰기를 위해 MODE_PRIVATE나 MODE_APPEND를 사용할 수 있다.
                    String str = "Do it! 안드로이드 앱 프로그래밍!";
                    fos.write(str.getBytes());
                    // 위의 문자열을 파일에 쓴다.
                    // 이때 문자열을 getBytes()를 이용하여 byte[] 형으로 변환
                    fos.close();
                    Toast.makeText(getApplicationContext(),"파일 생성함",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"파일 생성 실패",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fis = openFileInput("file.txt");
                    byte[] txt = new byte[500];
                    fis.read(txt);
                    String str = new String(txt);
                    tv1.setText(str);
                    Toast.makeText(getApplicationContext(),str, Toast.LENGTH_SHORT).show();
                    fis.close();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"파일없음",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
