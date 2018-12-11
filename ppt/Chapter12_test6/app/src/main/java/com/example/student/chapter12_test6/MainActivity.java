package com.example.student.chapter12_test6;

import android.Manifest;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker dp1;
    EditText et1;
    Button btnW;
    String fileName;
    SQLiteDatabase sqlDB;
    myDBHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단한 일기장");

        dp1 = (DatePicker) findViewById(R.id.dp1);
        et1 = (EditText) findViewById(R.id.et1);
        btnW = (Button) findViewById(R.id.btnW);

        Calendar cal = Calendar.getInstance();

        int cYear = cal.get(Calendar.YEAR);
        final int cMontn = cal.get(Calendar.MONTH);
        final int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp1.init(cYear, cMontn, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String day = i + "." + i1 + "." + i2;
                String str = readDiary(day);
                et1.setText(str);
                btnW.setEnabled(true);
            }
        });

        btnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    String readDiary(String day) {
        String diaryStr = null;

        sqlDB = myHelper.getReadableDatabase();
        String dbDay = sqlDB.execSQL("");
        if () {
            btnW.setText("수정하기");
        } else {
            et1.setHint("일기 없음");
            btnW.setText("새로 저장");
        }
        return diaryStr;
    }

    public class myDBHelper extends SQLiteOpenHelper {

        public myDBHelper(Context context) {
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table myDiary (diaryDate char(10), content varchar(500));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists myDiary");
            onCreate(db);
        }
    }
}
