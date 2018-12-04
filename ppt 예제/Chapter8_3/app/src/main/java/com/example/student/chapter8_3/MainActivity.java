package com.example.student.chapter8_3;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnR;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnR = (Button)findViewById(R.id.btnR);
        et1 = (EditText)findViewById(R.id.et1);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream("sdcard/test.txt");
                    byte[] txt = new byte[fis.available()];
                    fis.read(txt);
                    et1.setText(new String(txt));
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
