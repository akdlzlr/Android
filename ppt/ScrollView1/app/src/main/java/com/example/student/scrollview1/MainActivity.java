package com.example.student.scrollview1;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView1;
    ImageView iv;
    BitmapDrawable bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스크롤 뷰를 이용한 예제1");

        scrollView1 = (ScrollView)findViewById(R.id.scrollView1);
        iv =(ImageView)findViewById(R.id.iv);
        scrollView1.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        iv.setImageDrawable(bitmap);
        iv.getLayoutParams().width=bitmapWidth;
        iv.getLayoutParams().height=bitmapHeight;

    }

    public void onChangeBtnClicked(View v){
        changeImage();
    }

    private void changeImage(){
        Resources res = getResources();

        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        iv.setImageDrawable(bitmap);
        iv.getLayoutParams().width=bitmapWidth;
        iv.getLayoutParams().height=bitmapHeight;
    }
}
