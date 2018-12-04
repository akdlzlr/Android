package com.example.student.chapter12_2_db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText et1, et2;
    TextView result1, result2;
    Button btn1, btn2, btn3, btn4, btn5;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        result1 = (TextView)findViewById(R.id.result1);
        result2 = (TextView)findViewById(R.id.result2);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);

        myHelper = new myDBHelper(this);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"초기화 완료", Toast.LENGTH_SHORT).show();
                btn5.callOnClick();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("insert into sampleDB values('"
                + et1.getText().toString()+"',"
                + et2.getText().toString()+");");

                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력 완료", Toast.LENGTH_SHORT).show();
                btn5.callOnClick();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("update sampleDB set gNumber="+et2.getText().toString()
                        +" where gName = '"+et1.getText().toString()+"';");

                sqlDB.close();
                Toast.makeText(getApplicationContext(),"수정 완료", Toast.LENGTH_SHORT).show();
                btn5.callOnClick();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("delete from sampleDB where gName ='"+et1.getText().toString()+"';");

                sqlDB.close();
                Toast.makeText(getApplicationContext(),"삭제 완료", Toast.LENGTH_SHORT).show();
                btn5.callOnClick();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;

                cursor = sqlDB.rawQuery("select * from sampleDB;",null);

                String strNames = "그룹 이름" +"\r\n"+"────────"+"\r\n";
                String strNumbers = "인원 " + "\r\n"+"────────"+"\r\n";

                while (cursor.moveToNext()){
                    strNames += cursor.getString(0)+"\r\n";
                    strNumbers +=cursor.getString(1)+"\r\n";
                }

                result1.setText(strNames);
                result2.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper{

        public myDBHelper(Context context){
            super(context,"test1.db",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table sampleDB (gName char(20), gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("drop table if exists sampleDB");
            onCreate(db);
        }
    }
}
