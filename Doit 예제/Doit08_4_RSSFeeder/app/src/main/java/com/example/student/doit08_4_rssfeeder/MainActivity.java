package com.example.student.doit08_4_rssfeeder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  static String rssUrl="http://api.sbs.co.kr/xml/news/rss.jsp?pmDiv=entertainment";

    ArrayList<RSSNewItem> newsItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new RSSListView(this);
        adapter = new RSSListAdapter(this);
        list.setAdapter(adapter);

        list.setOnDataS
    }
}
