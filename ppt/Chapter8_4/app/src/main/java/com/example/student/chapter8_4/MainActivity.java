package com.example.student.chapter8_4;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2,btn3;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        tv1 = (TextView)findViewById(R.id.tv1);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        final String strSDpath =
                Environment.getExternalStorageDirectory().getAbsolutePath();
        final File myDir = new File(strSDpath+"/mydir");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.mkdir();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDir.delete();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getRootDirectory().getAbsolutePath();
                File[] sysFiles = (new File(sysDir).listFiles());

                String strFname;
                for(int i =0; i<sysFiles.length;i++){
                    if(sysFiles[i].isDirectory()==true){
                        strFname = "<폴더>"+ sysFiles[i].toString();
                    }else{
                        strFname = "<파일>"+ sysFiles[i].toString();
                    }

                    tv1.setText(tv1.getText()+"\n"+strFname);
                }
            }
        });
    }
}
