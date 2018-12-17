package com.example.student.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

public class LoginActivity extends AppCompatActivity {

    TextView textView2;
    EditText editText, editText2, editText3;
    Button button;
    Boolean id = false, pwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView2 = (TextView) findViewById(R.id.textView2);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int size = editText.getText().toString().length();

                if (editText.getText().toString().equals("")){




                    textView2.setText("아이디를 입력하세요.");
                    return;
                }

                if (5 <= size && size <= 12) {
                    textView2.setText("정상적인 아이디 입니다.");
                    id = true;
                } else {
                    id = false;
                    textView2.setText("아이디는 5글자 이상 12글자 이하여야 합니다.");
                }
            }
        });

        editText2.addTextChangedListener(new MyTextWatcher());
        editText3.addTextChangedListener(new MyTextWatcher());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == true && pwd == true) {
                    Toast.makeText(getApplication(), "반갑습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, ReservationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "정보를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            String pwd1 = editText2.getText().toString();
            String pwd2 = editText3.getText().toString();

            if (pwd1.equals("") && pwd2.equals("")) {
                textView2.setText("비밀번호를 입력하세요.");
            } else if (pwd1.equals(pwd2)) {
                textView2.setText("비밀번호가 일치합니다.");
                pwd = true;
            } else {
                textView2.setText("비밀번호가 일치하지 않습니다.");
                pwd = false;
            }
        }
    }
}
