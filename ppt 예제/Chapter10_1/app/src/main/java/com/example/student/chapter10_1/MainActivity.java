package com.example.student.chapter10_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    RadioGroup rg;
    RadioButton rb1, rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        rg = (RadioGroup)findViewById(R.id.rg);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class nextPage;

                if(rg.getCheckedRadioButtonId()==R.id.rb1){
                    nextPage = Second.class;
                }else if (rg.getCheckedRadioButtonId()==R.id.rb2){
                    nextPage = Third.class;
                }else{
                    Toast.makeText(getApplicationContext(),"라디오 버튼 선택을 먼저 해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), nextPage);
                startActivity(intent);
            }
        });
    }
}
