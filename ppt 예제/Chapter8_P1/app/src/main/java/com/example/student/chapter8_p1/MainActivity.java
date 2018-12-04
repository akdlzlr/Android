package com.example.student.chapter8_p1;

import android.Manifest;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp1;
    EditText et1;
    Button btnW;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단한 일기장");

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);
        dp1 = (DatePicker)findViewById(R.id.dp1);
        et1 = (EditText)findViewById(R.id.et1);
        btnW = (Button)findViewById(R.id.btnW);
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File mydir = new File(strSDpath+"/mydir");
        Calendar cal =Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        final int cMontn = cal.get(Calendar.MONTH);
        final int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp1.init(cYear, cMontn, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                fileName = Integer.toString(i)+"_"+ Integer.toString(i1+1)+
                        "_"+Integer.toString(i2)+".txt";
                String str = readDiary(fileName);
                et1.setText(str);
                btnW.setEnabled(true);
            }
        });

        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    FileOutputStream fos = new FileOutputStream(mydir+"/"+fileName);
                    String str = et1.getText().toString();
                    fos.write(str.getBytes());
                    fos.close();
                    Toast.makeText(getApplicationContext(), "저장 완료",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                   mydir.mkdir();
                }


            }
        });
    }

    String readDiary(String fileName){
        String diaryStr = null;
        FileInputStream fis;
        final String strSDpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        final File mydir = new File(strSDpath+"/mydir");
        try {
            fis = new FileInputStream(mydir+"/"+fileName);
            byte[] txt = new byte[500];
            fis.read(txt);
            fis.close();
            diaryStr = (new String(txt)).trim();
            btnW.setText("수정하기");
        } catch (IOException e) {
            et1.setHint("일기 없음");
            btnW.setText("새로 저장");
        }


        return diaryStr;
    }
}
