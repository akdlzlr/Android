package com.example.student.chapter11_3_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("스피너 테스트!");

        final String[] movie = {
                "써니", "완득이", "괴물", "라디오스타", "비혈한 거리",
                "왕의 남자", "아일랜드", "웰컴투 동막골", "헬보이", "백투더퓨쳐",
                "여인의 향기", "쥬라기 공원", "포레스트 검프", "완득이", "혹성탈출",
                "완득이", "괴물", "완득이", "괴물", "완득이"};

        final Integer[] posterId = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,
                R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
                R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,
                R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
                R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,
                R.drawable.mov16,R.drawable.mov17,R.drawable.mov18,
                R.drawable.mov19,R.drawable.mov20};

        final Spinner spinner = (Spinner) findViewById(R.id.spin1);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,movie);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView ivPoster = (ImageView)findViewById(R.id.ivPoster);
                ivPoster.setImageResource(posterId[spinner.getSelectedItemPosition()]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}
