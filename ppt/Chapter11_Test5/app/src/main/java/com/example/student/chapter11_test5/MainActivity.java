package com.example.student.chapter11_test5;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Integer[] imageId = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};
    String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
            "에리느깡 단 베르망", "잠자는 소녀", "테라스의 두자매",
            "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

    final int voteCount[] = new int[9];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");

        final GridView gv = (GridView)findViewById(R.id.gv1);
        MyGridAdapter gridAdapter = new MyGridAdapter(this);
        gv.setAdapter(gridAdapter);


        for(int i =0;i<9;i++){
            voteCount[i]=0;
        }

        Button btnFinish;
        btnFinish = (Button)findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        Result.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);

                startActivity(intent);
            }
        });

    }

    public class MyGridAdapter extends BaseAdapter{
        Context c;

        public MyGridAdapter(Context context){
            c=context;
        }
        @Override
        public int getCount() {
            return imageId.length;
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
            ImageView imageView = new ImageView(c);
            imageView.setLayoutParams(new GridView.LayoutParams(400,650));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(1,10,1,5);

            imageView.setImageResource(imageId[i]);
            final int index = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    if (voteCount[index]>=5) voteCount[index]=5;
                    Toast.makeText(getApplicationContext(),imgName[index]+": 총 "+voteCount[index]+" 표",Toast.LENGTH_SHORT).show();
                }
            });

            return imageView;
        }
    }
}
