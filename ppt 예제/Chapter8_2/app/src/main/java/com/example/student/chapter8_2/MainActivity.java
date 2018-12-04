package com.example.student.chapter8_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        et1 = (EditText) findViewById(R.id.et1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InputStream is = getResources().openRawResource(R.raw.test);
                    byte[] txt = new byte[is.available()];
                    is.read(txt);
                    et1.setText(new String(txt));
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
