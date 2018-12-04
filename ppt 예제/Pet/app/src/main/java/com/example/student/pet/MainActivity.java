package com.example.student.pet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox start;
    TextView text1;
    RadioGroup rg;
    RadioButton dog, rabbit, cat;
    ImageView iv;
    Button selectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("애완동물");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);



        start = (CheckBox)findViewById(R.id.start);
        text1 = (TextView)findViewById(R.id.text1);
        rg = (RadioGroup)findViewById(R.id.rg);
        dog = (RadioButton)findViewById(R.id.dog);
        cat = (RadioButton)findViewById(R.id.cat);
        rabbit = (RadioButton)findViewById(R.id.rabbit);

        iv = (ImageView)findViewById(R.id.iv);
        selectBtn = (Button)findViewById(R.id.selectBtn);

        start.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(start.isChecked()==true){
                    text1.setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    selectBtn.setVisibility(View.VISIBLE);
                    iv.setVisibility(View.VISIBLE);
                }else{
                    text1.setVisibility(View.INVISIBLE);
                    rg.setVisibility(View.INVISIBLE);
                    selectBtn.setVisibility(View.INVISIBLE);
                    iv.setVisibility(View.INVISIBLE);
                }
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.dog:
                        iv.setImageResource(R.drawable.dog);
                        break;
                    case R.id.rabbit:
                        iv.setImageResource(R.drawable.rabbit);
                        break;
                    case R.id.cat:
                        iv.setImageResource(R.drawable.cat);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"동물 먼저 선택하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
