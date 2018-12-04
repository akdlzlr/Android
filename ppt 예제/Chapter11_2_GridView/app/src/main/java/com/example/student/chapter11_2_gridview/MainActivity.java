package com.example.student.chapter11_2_gridview;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Integer[] posterId = {
            R.drawable.mov01, R.drawable.mov02, R.drawable.mov03,
            R.drawable.mov04,R.drawable.mov05,R.drawable.mov06,
            R.drawable.mov07,R.drawable.mov08,R.drawable.mov09,
            R.drawable.mov10,R.drawable.mov11,R.drawable.mov12,
            R.drawable.mov13,R.drawable.mov14,R.drawable.mov15,
            R.drawable.mov16,R.drawable.mov17,R.drawable.mov18,
            R.drawable.mov19,R.drawable.mov20};

    String[] posterName = {
            "써니", "완득이", "괴물", "완득이", "괴물",
            "완득이", "괴물", "완득이", "괴물", "완득이",
            "괴물", "완득이", "괴물", "완득이", "괴물",
            "완득이", "괴물", "완득이", "괴물", "완득이"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드 뷰 영화 포스터");

        final GridView gv = (GridView)findViewById(R.id.gv1);

        MyGridAdapter mga = new MyGridAdapter(this);
        gv.setAdapter(mga);


           }

    public class MyGridAdapter extends BaseAdapter{
        Context context;

        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterId.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200,300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5,5,5,5);

            imageView.setImageResource(posterId[i]);

            final int pos = i;

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView)dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterId[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });


            return imageView;
        }
    }
}
