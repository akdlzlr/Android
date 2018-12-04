package com.example.student.chapter14_3;

import android.Manifest;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        et1 = (EditText)findViewById(R.id.et1);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_CALL_LOG},MODE_PRIVATE);
        //API 23 버전(마시멜로)부터는 직접 퍼미션을 허용해주는 코드가 필요.
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1.setText(getCallHistory());
            }
        });
    }

    public String getCallHistory(){
        String[] callSet = new String[]{CallLog.Calls.DATE, CallLog.Calls.TYPE, CallLog.Calls.NUMBER,
        CallLog.Calls.DURATION};
        StringBuffer callBuff = new StringBuffer();
        Cursor c=null;
        try{
            c = getContentResolver().query(CallLog.Calls.CONTENT_URI, callSet,
                    null,null,null);
        }catch (SecurityException e){
            return callBuff.append("예외 발생").toString();
        }
        if(c == null){
            return "통화기록 없음";
        }


        callBuff.append("\n 날짜 : 구분 : 전화번호 : 통화시간\n\n");
        c.moveToFirst();

        do{
            long callDate = c.getLong(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = dateFormat.format(new Date(callDate));

            callBuff.append(date_str+":");
            if(c.getInt(1)==    CallLog.Calls.INCOMING_TYPE){
                callBuff.append("착신 : ");
            }else {
                callBuff.append("발신 : ");
            }
            callBuff.append(c.getString(2)+":");
            callBuff.append(c.getString(3)+"초\n");

        }while (c.moveToNext());

        c.close();
        return callBuff.toString();
    }


}
