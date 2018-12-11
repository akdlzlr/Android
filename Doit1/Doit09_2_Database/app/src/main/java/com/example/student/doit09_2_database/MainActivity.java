package com.example.student.doit09_2_database;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button btn1, btn2;
    EditText et1,et2;
    String databaseName, tableName;
    TextView status;
    boolean databaseCreated = false;
    boolean tableCreated = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        status = (TextView)findViewById(R.id.status);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseName = et1.getText().toString();
                createDatabase(databaseName);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = et2.getText().toString();
                createTable(tableName);
                int count = insertRecord(tableName);
                println(count + " records inserted.");
            }
        });
    }

    private void createDatabase(String name){
        println("creating database ["+name+"].");

        db = openOrCreateDatabase(name, Activity.MODE_PRIVATE,null);
        databaseCreated = true;
    }

    private void createTable(String name){
        println("createing table [" + name + "].");
        db.execSQL("create table "+name+"("
        +" _id integer PRIMARY KEY autoincrement, "
        +" name text, "
        +" age integer, "
        +" phone text);");

        tableCreated = true;
    }

    private int insertRecord(String name) {

        println("inserting records.");

        int count = 3;

        db.execSQL( "insert into " + name + "(name, age, phone) values('John',20,'010-7788-1234');");
        db.execSQL( "insert into " + name + "(name, age, phone) values ('Mike', 35, '010-8888-1111');" );
        db.execSQL( "insert into " + name + "(name, age, phone) values ('Sean', 26, '010-6677-4321');" );

        return count;
    }

    private int updateRecordParam(String name) {
        println("updating records using parameters.");

        ContentValues recordValues = new ContentValues();
        recordValues.put("age", 43);
        String[] whereArgs = {"Rice"};

        int rowAffected = db.update(name,
                recordValues,
                "name = ?",
                whereArgs);

        return rowAffected;
    }

    private int deleteRecordParam(String name) {
        println("deleting records using parameters.");

        String[] whereArgs = {"Rice"};

        int rowAffected = db.delete(name,
                "name = ?",
                whereArgs);

        return rowAffected;
    }

    private void println(String msg) {
        Log.d("MainActivity", msg);
        status.append("\n" + msg);

    }
}
