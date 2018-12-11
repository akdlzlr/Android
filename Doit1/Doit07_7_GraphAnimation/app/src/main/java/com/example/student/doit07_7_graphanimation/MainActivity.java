package com.example.student.doit07_7_graphanimation;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Resources res;
    Animation growAnim;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();

        growAnim = AnimationUtils.loadAnimation(this, R.anim.grow);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        addItem("apple", 80);
        addItem("orange", 100);
        addItem("kiwi", 40);
    }

    private void addItem(String name, int value) {
        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setText(name);
        params.width = 180;
        params.setMargins(0, 4, 0, 4);
        itemLayout.addView(textView, params);

        ProgressBar proBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        proBar.setIndeterminate(false);
        proBar.setMax(100);
        proBar.setProgress(100);
        proBar.setAnimation(growAnim);
        params2.height = 80;
        params2.width = value * 3;
        params2.gravity = Gravity.LEFT;

        itemLayout.addView(proBar, params2);

        mainLayout.addView(itemLayout, params3);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Toast.makeText(this, "onWindowFocusChanged : "+hasFocus,Toast.LENGTH_SHORT).show();
        if (hasFocus) {
            growAnim.start();
        }else{
            growAnim.reset();
        }
    }
}
