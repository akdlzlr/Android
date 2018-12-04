package com.example.student.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("리스트 뷰 테스트");

        final ArrayList<String> mid = new ArrayList<>();
        mid.add(0,"안녕");

        ListView list = (ListView)findViewById(R.id.view1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,mid);

        list.setAdapter(adapter);


        final EditText ed1 = (EditText)findViewById(R.id.ed1);
        Button btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mid.add(ed1.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });



        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                mid.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
