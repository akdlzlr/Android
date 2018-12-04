package com.example.student.doittest03;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView1,scrollView2;
    ImageView iv1,iv2;
    BitmapDrawable bitmap;
    Button upBtn,dropBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("이미지 번갈아 보여주기");

        scrollView1 = (ScrollView)findViewById(R.id.scrollView1);
        iv1 =(ImageView)findViewById(R.id.iv1);
        upBtn = (Button)findViewById(R.id.upBtn);
        dropBtn = (Button)findViewById(R.id.dropBtn);
        scrollView1.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        iv1.setImageDrawable(bitmap);
        iv1.getLayoutParams().width=bitmapWidth;
        iv1.getLayoutParams().height=bitmapHeight;

        scrollView2 = (ScrollView)findViewById(R.id.scrollView2);
        iv2 =(ImageView)findViewById(R.id.iv2);
        scrollView2.setHorizontalScrollBarEnabled(true);

        Resources res2 = getResources();
        bitmap = (BitmapDrawable) res2.getDrawable(R.drawable.image01);
        int bitmapWidth2 = bitmap.getIntrinsicWidth();
        int bitmapHeight2 = bitmap.getIntrinsicHeight();

        iv2.setImageDrawable(bitmap);
        iv2.getLayoutParams().width=bitmapWidth;
        iv2.getLayoutParams().height=bitmapHeight;
        iv2.setVisibility(View.INVISIBLE);

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.INVISIBLE);
            }
        });

        dropBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv1.setVisibility(View.INVISIBLE);
                iv2.setVisibility(View.VISIBLE);
            }
        });
    }
}
