package com.example.student.doit05_3_listview01;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText editText;

    ListView listView;
    SingerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new SingerAdapter();

        adapter.addItem(new SingerItem("소녀시대","010-1000-1000",27, R.drawable.singer));
        adapter.addItem(new SingerItem("걸스데이","010-2000-2000",25, R.drawable.singer2));
        adapter.addItem(new SingerItem("여자친구","010-3000-3000",22, R.drawable.singer3));
        adapter.addItem(new SingerItem("레드벨벳","010-4000-4000",23, R.drawable.singer4));
        adapter.addItem(new SingerItem("트와이스","010-5000-5000",20, R.drawable.singer5));

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SingerItem item = (SingerItem) adapter.getItem(i);
                Toast.makeText(getApplicationContext(), "선택 : "+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    class SingerAdapter extends BaseAdapter {
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View converView, ViewGroup viewGroup) {
            SingerItemView view = new SingerItemView(getApplicationContext());

            SingerItem item = items.get(i);

            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setAge(item.getAge());
            view.setImage(item.getResId());

            return view;
        }
    }
}
