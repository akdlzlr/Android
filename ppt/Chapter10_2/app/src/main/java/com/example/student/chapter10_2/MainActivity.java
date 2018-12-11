package com.example.student.chapter10_2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");

        final int voteCount[] = new int[9];

        for(int i =0;i<9;i++){
            voteCount[i]=0;
        }

        ImageView image[] = new ImageView[9];
        Integer imageId[] = {R.id.iv1, R.id.iv2, R.id.iv3,
                R.id.iv4, R.id.iv5, R.id.iv6,
                R.id.iv7, R.id.iv8, R.id.iv9};

        final String imgName[] = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "에리느깡 단 베르망", "잠자는 소녀", "테라스의 두자매",
                "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        for(int i=0; i<imageId.length;i++){
            final int index;
            index  = i;
            image[index] = (ImageView)findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    voteCount[index]++;
                    if (voteCount[index]>=5) voteCount[index]=5;
                    Toast.makeText(getApplicationContext(),imgName[index]+": 총 "+voteCount[index]+" 표",Toast.LENGTH_SHORT).show();
                }
            });
        }

        Button btnFinish;
        btnFinish = (Button)findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                                ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imgName);

                startActivity(intent);
            }
        });

    }
}
