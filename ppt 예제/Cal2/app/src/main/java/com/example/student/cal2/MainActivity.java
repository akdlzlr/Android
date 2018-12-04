package com.example.student.cal2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button sum, sub, mul, div;
    TextView resultText;

    String num1, num2;
    Double result;

    Button[] numButtons = new Button[10];
    Integer[] numButtonIDs = {R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3,
            R.id.BtnNum4, R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7,
            R.id.BtnNum8, R.id.BtnNum9};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("버튼형 계산기");

        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);

        sum = (Button) findViewById(R.id.sum);
        sub = (Button) findViewById(R.id.sub);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);

        resultText = (TextView) findViewById(R.id.result);

        for (int i = 0; i < numButtonIDs.length; i++) {
            numButtons[i] = (Button) findViewById(numButtonIDs[i]);
        }


        for (int i = 0; i < numButtonIDs.length; i++) {
            final int index;
            index = i;


            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edit1.isFocused() == true) {
                        num1 = edit1.getText().toString() +
                                numButtons[index].getText().toString();
                        edit1.setText(num1);

                    } else if (edit2.isFocused() == true) {
                        if (edit2.isFocused() == true) {
                            num2 = edit2.getText().toString() +
                                    numButtons[index].getText().toString();
                            edit2.setText(num2);

                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"에디트 텍스트를 먼저 선택하세요",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();

                if (num1.isEmpty() || num2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                result = Double.parseDouble(num1) + Double.parseDouble(num2);

                resultText.setText(result.toString());

            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (num1.equals("") || num2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = Double.parseDouble(num1) - Double.parseDouble(num2);

                resultText.setText(result.toString());

            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (num1.isEmpty() || num2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = Double.parseDouble(num1) * Double.parseDouble(num2);

                resultText.setText(result.toString());

            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if (num1.isEmpty() || num2.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.parseDouble(num2) == 0) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                result = Double.parseDouble(num1) / Double.parseDouble(num2);

                resultText.setText(result.toString());

            }
        });


    }
}
