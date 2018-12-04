package com.example.student.sampleparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onBtn1(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        SampleData sd = new SampleData(100, "Hello Android!");
        intent.putExtra(KEY_SIMPLE_DATA, sd);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Intent intent =getIntent();
        String str = intent.getStringExtra("Name");

        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

    }
}
