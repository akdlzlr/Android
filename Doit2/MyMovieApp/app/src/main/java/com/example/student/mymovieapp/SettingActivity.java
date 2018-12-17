package com.example.student.mymovieapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    Button auto, autoCancle, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        auto = (Button)findViewById(R.id.auto);
        autoCancle = (Button)findViewById(R.id.autoCancle);
        back = (Button)findViewById(R.id.btnBack);

        final SharedPreferences sharedPref = getSharedPreferences("userdata", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("auto", true);
                editor.commit();
            }
        });

        autoCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("auto", false);
                editor.commit();
            }
        });
    }
}
