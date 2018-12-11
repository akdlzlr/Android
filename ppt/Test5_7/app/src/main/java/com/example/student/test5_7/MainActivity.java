package com.example.student.test5_7;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setTitle("연습문제 5-7");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(params);

        LinearLayout upLayout = new LinearLayout(this);
        upLayout.setOrientation(LinearLayout.HORIZONTAL);
        upLayout.setLayoutParams(params);

        LinearLayout upleftLayout = new LinearLayout(this);
        upleftLayout.setLayoutParams(params);
        upleftLayout.setBackgroundColor(Color.RED);

        upLayout.addView(upleftLayout);

        LinearLayout uprightLayout = new LinearLayout(this);
        uprightLayout.setLayoutParams(params);
        uprightLayout.setOrientation(LinearLayout.VERTICAL);
        uprightLayout.setBackgroundColor(Color.BLUE);

        upLayout.addView(uprightLayout);
        







    }
}
