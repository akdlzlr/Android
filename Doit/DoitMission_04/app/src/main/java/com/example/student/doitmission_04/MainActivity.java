package com.example.student.doitmission_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    TextView tv;
    Button button, button2;
    int strByte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        tv = (TextView) findViewById(R.id.tv);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        et1.addTextChangedListener(new MyTextWatcher());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), et1.getText().toString(), Toast.LENGTH_SHORT).show();
                et1.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            strByte = et1.getText().toString().getBytes().length;
            tv.setText(strByte+"/80 Byte");
            if(strByte>=80){
                et1.setEnabled(false);
            }else {
                et1.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
