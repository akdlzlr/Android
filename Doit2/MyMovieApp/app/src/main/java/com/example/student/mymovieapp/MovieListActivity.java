package com.example.student.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.mymovieapp.format.ListViewAdapter;
import com.example.student.mymovieapp.format.Movie;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {

    final String[] mid = {"블랙팬서", "궁합", "리틀 포레스트", "월요일이 사라졌다"};
    Button btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetting = (Button)findViewById(R.id.btnSetting);

        // 2. 리스트뷰 객체 만들기
        ListView list = (ListView) findViewById(R.id.ListView);

        ArrayList<Movie> arrayList = new ArrayList<Movie>();

        Movie item = new Movie(
                "블랙팬서", "2018. 3", R.drawable.black);

        arrayList.add(item);
        arrayList.add(
                new Movie("궁합", "2018. 1", R.drawable.match));
        arrayList.add(
                new Movie(
                        "리틀포레스트", "2018. 11", R.drawable.little));
        arrayList.add(
                new Movie(
                        "월요일이 사라졌다", "2018. 12", R.drawable.monday));


        // 3. 리스트뷰에 adapter 등록하기
            /*  첫번째 매개변수 : 액티비티 정보(context 객체)
                두번째 매개변수 : 리스트뷰 항목의 레이아웃(안드로이드 제공)
                세번째 매개변수 : 표시할 데이터들 */
        ListViewAdapter adapter = new ListViewAdapter(MovieListActivity.this,
                R.layout.movie_item, arrayList);
        list.setAdapter(adapter);

        // 4. 리스트 뷰에 onItemClickListener 등록하기
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hi~", Toast.LENGTH_SHORT).show();
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieListActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
