package com.example.student.mymovieapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ReservationActivity extends AppCompatActivity {

    TextView tvCall, tvWeb, tvMap;
    Button btnCall, btnWeb, btnMap, btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        tvCall = (TextView)findViewById(R.id.tvCall);
        tvWeb = (TextView)findViewById(R.id.tvWeb);
        tvMap = (TextView)findViewById(R.id.tvMap);

        btnCall = (Button)findViewById(R.id.btnCall);
        btnWeb = (Button)findViewById(R.id.btnWeb);
        btnMap = (Button)findViewById(R.id.btnMap);
        btnFinish = (Button)findViewById(R.id.btnFinish);

        MoveClickListener moveClickListener = new MoveClickListener();

        btnCall.setOnClickListener(moveClickListener);
        btnWeb.setOnClickListener(moveClickListener);
        btnMap.setOnClickListener(moveClickListener);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    class MoveClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent=null;

            switch(v.getId()){
                case R.id.btnCall:
                    String num = tvCall.getText().toString();
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("tel:"+num));
                    break;
                case R.id.btnWeb:
                    String address = tvWeb.getText().toString();
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://"+address));
                    break;
                case R.id.btnMap:
                    String geo = "37.5017, 127.0397597";
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo:"+geo));
                    break;
            }
            if(intent!=null){
                startActivity(intent);
            }
        }
    }
}
