package com.example.student.chapter6_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView actv1;
    MultiAutoCompleteTextView mactv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("자동 완성 예제");

        String[] items = {"CSI-뉴욕","CSI-라스베가스","CSI-마이애미","Friends", "Fringe", "Lost"};

        actv1 = (AutoCompleteTextView)findViewById(R.id.actv1);
        mactv1 = (MultiAutoCompleteTextView)findViewById(R.id.mactv1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,items);

        actv1.setAdapter(adapter);

        MultiAutoCompleteTextView.CommaTokenizer token =
                new MultiAutoCompleteTextView.CommaTokenizer();
        mactv1.setTokenizer(token);
        mactv1.setAdapter(adapter);
    }
}
