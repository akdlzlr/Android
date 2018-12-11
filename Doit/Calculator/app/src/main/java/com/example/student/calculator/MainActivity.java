package com.example.student.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnMul, btnSub, btnDiv,
            btnSum, btnClear, btnResult, btnDot;
    Button[] btn = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
    Integer[] numButtonIDs = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9};
    EditText et1;

    Double num1=0.0, num2=.0;
    Double result;
    int sel=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDot = (Button) findViewById(R.id.btnDot);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnSum = (Button) findViewById(R.id.btnSum);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnResult = (Button) findViewById(R.id.btnResult);
        et1 = (EditText) findViewById(R.id.et1);

        for (int i = 0; i < 10; i++) {
            final int a = i;
            btn[a] = (Button) findViewById(numButtonIDs[a]);
        }

        for (int i = 0; i < 10; i++) {
            final int a = i;
            btn[a].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    et1.append(a + "");
                }
            });
        }

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.append(".");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText("");
                num1 = 0.0;
                num2 = 0.0;
                result = 0.0;
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(et1.getText().toString());
                sel=3;
                et1.setText("");
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(et1.getText().toString());
                sel=2;
                et1.setText("");
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(et1.getText().toString());
                sel=1;
                et1.setText("");
            }
        });

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = Double.parseDouble(et1.getText().toString());
                sel=0;
                et1.setText("");
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2= Double.parseDouble(et1.getText().toString());
                switch (sel){
                    case 0:
                        result=num1+num2;
                        break;
                    case 1:
                        result=num1-num2;
                        break;
                    case 2:
                        result=num1/num2;
                        break;
                    case 3:
                        result=num1*num2;
                        break;
                }
                et1.setText(result+"");
            }
        });
    }
}
