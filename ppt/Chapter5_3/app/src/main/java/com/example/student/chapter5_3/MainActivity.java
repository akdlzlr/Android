package com.example.student.chapter5_3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

        LinearLayout baseLayout = new LinearLayout(this);

        baseLayout.setOrientation(LinearLayout.VERTICAL);

        baseLayout.setBackgroundColor(Color.rgb(0, 210,0));
        setContentView(baseLayout, params);

        Button button = new Button(this);
        button.setText("버튼입니다.");
        button.setBackgroundColor(Color.MAGENTA);

        baseLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"안녕!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
