package com.example.student.chapter10_test6;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public class Result extends AppCompatActivity {

    Button btn, start, stop;
    ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        //String[] imageName = intent.getStringArrayExtra("ImageName");

        btn = (Button) findViewById(R.id.btnReturn);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        vf = (ViewFlipper) findViewById(R.id.view1);

        ImageView image[] = new ImageView[9];

        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3,
                R.id.iv4, R.id.iv5, R.id.iv6,
                R.id.iv7, R.id.iv8, R.id.iv9};

        for (int i = 0; i < imageId.length; i++) {
            final int index;
            index = i;
            image[index] = (ImageView) findViewById(imageId[index]);
        }

        ArrayList<Integer> sortedImage = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {
            sortedImage.add(imageId[i], voteResult[i]);
        }

        Collections.sort(sortedImage);
        android.util.Log.i(sortedImage.toString());

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.setFlipInterval(500);
                vf.startFlipping();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.stopFlipping();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //	swap함수
    public static void swap(int[] a, int i1, int i2) {
        int temp;
        temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }

    //	버블정렬
    public static void sortByBubble(int[] a) {

        for (int j = 0; j < a.length; j++) {
            for (int i = 0; i < (a.length - j - 1); i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                }
            }
        }
    }
}
