package com.example.student.test1_20181211;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int a =1;
        a=2;
        a=3;
        a=4;

        a=-1;
        Toast.makeText(this, a+"", Toast.LENGTH_SHORT).show();

    }
}