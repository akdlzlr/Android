package com.example.student.mymovieapp.format;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.mymovieapp.InfoActivity;
import com.example.student.mymovieapp.R;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-14.
 */

public class ListViewAdapter  extends BaseAdapter{

    ArrayList<Movie> items = new ArrayList<Movie>();
    Context context;
    int item_layout;
    LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final int pos = position;

        if(view == null) {
            view = layoutInflater.inflate(item_layout, viewGroup, false);
        }

        ImageView iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);
        iv_thumb.setImageResource(items.get(pos).getId());
        iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,
                        items.get(pos).getName()+"를(을) 선택했습니다.",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("movie_index", pos);
                intent.putExtra("movie_name", items.get(pos).getName());
                intent.putExtra("movie_date", items.get(pos).getDate());
                intent.putExtra("movie_id", items.get(pos).getId());
                context.startActivity(intent);
            }
        });


        TextView tv_title = (TextView)view.findViewById(R.id.tv_title);
        tv_title.setText(items.get(pos).getName());

        TextView tv_date = (TextView)view.findViewById(R.id.tv_date);
        tv_date.setText(items.get(pos).getDate());

        return view;
    }

    public void addItem(Movie item){
        items.add(item);
    }

    public ListViewAdapter(
            Context context,
            int item_layout,
            ArrayList<Movie> list) {
        this.context = context;
        this.item_layout = item_layout;
        this.items = list;
        layoutInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }
}
