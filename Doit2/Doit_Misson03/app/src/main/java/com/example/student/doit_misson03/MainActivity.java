package com.example.student.doit_misson03;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv1, iv2;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = (ImageView)findViewById(R.id.iv1);
        iv2 = (ImageView)findViewById(R.id.iv2);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;

        Bitmap bitmap_city = BitmapFactory.decodeResource(getResources(), R.drawable.city, options);
        iv1.setImageBitmap(bitmap_city);
        iv2.setImageBitmap(bitmap_city);
        iv1.setVisibility(View.VISIBLE);
        iv2.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.INVISIBLE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv1.setVisibility(View.INVISIBLE);
                iv2.setVisibility(View.VISIBLE);
            }
        });

    }
}
