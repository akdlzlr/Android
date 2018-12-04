package com.example.student.hellow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onBackButtonCliked(View view){

        Toast.makeText(getApplicationContext(),"돌아가는 중...",Toast.LENGTH_SHORT).show();
        finish();
    }
}
