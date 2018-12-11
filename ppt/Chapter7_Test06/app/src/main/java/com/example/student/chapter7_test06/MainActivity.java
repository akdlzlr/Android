package com.example.student.chapter7_test06;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    RadioGroup rg;
    RadioButton dog, cat, rabbit, horse;
    View dialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("동물 사진 보기");
        btn1 = (Button)findViewById(R.id.btn1);
        rg = (RadioGroup)findViewById(R.id.rg);
        dog = (RadioButton)findViewById(R.id.dog);
        cat = (RadioButton)findViewById(R.id.cat);
        rabbit = (RadioButton)findViewById(R.id.rabbit);
        horse = (RadioButton)findViewById(R.id.horse);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogView = (View)View.inflate(MainActivity.this,R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                ImageView iv = (ImageView)dialogView.findViewById(R.id.iv);

                switch (rg.getCheckedRadioButtonId()){
                    case R.id.dog:
                        iv.setImageResource(R.drawable.dog);
                        dlg.setTitle("강아지");
                        break;
                    case R.id.cat:
                        iv.setImageResource(R.drawable.cat);
                        dlg.setTitle("고양이");
                        break;
                    case R.id.rabbit:
                        iv.setImageResource(R.drawable.rabbit);
                        dlg.setTitle("토끼");
                        break;
                    case R.id.horse:
                        iv.setImageResource(R.drawable.horse);
                        dlg.setTitle("말");
                        break;
                }

                dlg.setView(dialogView);
                dlg.setNegativeButton("닫기",null);

                dlg.show();
            }
        });
    }
}
