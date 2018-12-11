package com.example.student.calculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1,edit2;

    Button sum, sub, mul, div,nmg;

    TextView textResult;

    Double result;

    String num1,num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);

        sum = (Button)findViewById(R.id.sum);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        nmg = (Button)findViewById(R.id.nmg);

        textResult = (TextView)findViewById(R.id.result);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                if(num1.isEmpty()||num2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }

                result = Double.parseDouble(num1) + Double.parseDouble(num2);

                textResult.setText(result.toString());

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("")||num2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                result = Double.parseDouble(num1) - Double.parseDouble(num2);

                textResult.setText(result.toString());

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.isEmpty()||num2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                result = Double.parseDouble(num1) * Double.parseDouble(num2);

                textResult.setText(result.toString());

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.isEmpty()||num2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Double.parseDouble(num2)==0){
                    Toast.makeText(getApplicationContext(),"0으로 나눌수 없습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                result = Double.parseDouble(num1) / Double.parseDouble(num2);

                textResult.setText(result.toString());

            }
        });

        nmg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.isEmpty()||num2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"값을 입력하세요",Toast.LENGTH_SHORT).show();
                    return;
                }
               if(Double.parseDouble(num2)==0){
                    Toast.makeText(getApplicationContext(),"0으로 나눌수 없습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

                result = Double.parseDouble(num1) % Double.parseDouble(num2);

                textResult.setText(result.toString());

            }
        });

    }
}
