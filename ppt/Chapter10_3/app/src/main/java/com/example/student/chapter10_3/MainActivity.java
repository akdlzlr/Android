package com.example.student.chapter10_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    EditText num1, num2;
    RadioGroup rg;
    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);
        rg = (RadioGroup) findViewById(R.id.rg);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.sum:
                        x=0;
                        break;
                    case R.id.sub:
                        x=1;
                        break;
                    case R.id.mul:
                        x=2;
                        break;
                    case R.id.div:
                        x=3;
                        break;
                }
                Intent intent = new Intent(getApplicationContext(),Second.class);
                intent.putExtra("Num1",Integer.parseInt(num1.getText().toString()));
                intent.putExtra("Num2",Integer.parseInt(num2.getText().toString()));
                intent.putExtra("X",x);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            int hap = data.getIntExtra("Hap",0);
            Toast.makeText(getApplicationContext(),"합계 : "+hap,Toast.LENGTH_SHORT).show();
        }
    }
}
