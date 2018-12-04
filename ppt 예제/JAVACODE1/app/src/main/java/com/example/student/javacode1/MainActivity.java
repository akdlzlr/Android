package com.example.student.javacode1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout mainlayout = new LinearLayout(this);


        mainlayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button button01 = new Button(this);
        button01.setText("버튼 01");
        button01.setLayoutParams(params);

        Button button02 = new Button(this);
        button02.setText("버튼 02");
        button02.setLayoutParams(params);

        Button button03 = new Button(this);
        button03.setText("버튼 03");
        button03.setLayoutParams(params);

        TextView textView01 = new TextView(this);
        textView01.setText("텍스트 뷰01");
        textView01.setTextSize(30);
        textView01.setLayoutParams(params);


        mainlayout.addView(button01);
        mainlayout.addView(button02);
        mainlayout.addView(button03);
        mainlayout.addView(textView01);


        setContentView(mainlayout);


    }
}
