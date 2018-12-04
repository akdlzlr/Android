package com.example.student.chapter7_1_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        iv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chu:
                iv.setImageResource(R.drawable.jeju2);
                item.setChecked(true);
                return true;
            case R.id.han:
                iv.setImageResource(R.drawable.jeju1);
                item.setChecked(true);
                return true;
            case R.id.bum:
                iv.setImageResource(R.drawable.jeju3);
                item.setChecked(true);
                return true;
            case  R.id.imgeRotate:
                iv.setRotation(Float.parseFloat(editText.getText().toString()));
                return true;
        }


        return false;
    }
}
