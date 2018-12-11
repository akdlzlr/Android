package com.example.student.doit07_5_threadanimation;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    ArrayList<Drawable> drawbleList = new ArrayList<Drawable>();

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

        startAnimation();
    }

    public void startAnimation(){

        drawbleList.add(getResources().getDrawable(R.drawable.emo_im_laughing));
        drawbleList.add(getResources().getDrawable(R.drawable.emo_im_crying));
        drawbleList.add(getResources().getDrawable(R.drawable.emo_im_happy));
        drawbleList.add(getResources().getDrawable(R.drawable.emo_im_sad));
        drawbleList.add(getResources().getDrawable(R.drawable.emo_im_surprised));

        AnimThread thread = new AnimThread();
        thread.start();
    }

    class AnimThread extends Thread{
        public void run(){
            int index = 0;
            for(int i=0; i<100; i++){
                final Drawable drawable = drawbleList.get(index);
                index +=1;
                if(index>4){
                    index=0;
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try{
                    Thread.sleep(200);
                }catch (Exception e){}
            }
        }
    }
}
