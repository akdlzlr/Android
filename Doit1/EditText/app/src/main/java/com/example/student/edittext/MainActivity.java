package com.example.student.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tv1);
        et1 = (EditText)findViewById(R.id.et);

        et1.addTextChangedListener(new MyTextWatcher());

    }

    class MyTextWatcher implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            tv.setText(tv.getText().toString() + "\n수정되기 전 : "+s.toString());
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            tv.setText(tv.getText().toString() + "\n수정되기 중간 : "+s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
            tv.setText(tv.getText().toString() + "\n수정되기 후 : "+s.toString());
        }
    }
}