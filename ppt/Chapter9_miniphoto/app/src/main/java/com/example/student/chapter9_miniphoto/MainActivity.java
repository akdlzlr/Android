package com.example.student.chapter9_miniphoto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    ImageButton idZoomin, idZoomout, idRotate, idBright, idDark, idGray;
    MyGraphicView mgv;
    static float scaleX=1, scaleY=1, angle=0, color=1, satur=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("미니포토샵");

        LinearLayout pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        mgv = (MyGraphicView) new MyGraphicView(this);
        pictureLayout.addView(mgv);
        ClickIcons();
    }

    private static class MyGraphicView extends View {

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            canvas.scale(scaleX, scaleY, cenX, cenY);

            canvas.rotate(angle, cenX, cenY);

            Paint paint = new Paint();

            float[] array = { color, 0, 0, 0, 0,
                              0, color, 0, 0, 0,
                              0, 0, color, 0, 0,
                              0, 0, 0, 1, 0 };
            ColorMatrix cm = new ColorMatrix(array);
            if(satur==0) cm.setSaturation(satur);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture = BitmapFactory.decodeResource(getResources(),R.drawable.lena256);

            int picX = (this.getWidth()-picture.getWidth())/2;
            int picY = (this.getHeight()-picture.getHeight())/2;

            canvas.drawBitmap(picture, picX, picY, paint);

            picture.recycle();
        }
    }

    private void ClickIcons(){
        idZoomin = (ImageButton) findViewById(R.id.idZoomin);
        idZoomout = (ImageButton) findViewById(R.id.idZoomout);
        idRotate = (ImageButton) findViewById(R.id.idRotate);
        idBright = (ImageButton) findViewById(R.id.idBright);
        idDark = (ImageButton) findViewById(R.id.idDark);
        idGray = (ImageButton) findViewById(R.id.idGray);

        idZoomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX+=0.2f;
                scaleY+=0.2f;
                mgv.invalidate();

            }
        });


        idZoomout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scaleX-=0.2f;
                scaleY-=0.2f;
                mgv.invalidate();

            }
        });

        idRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angle +=20;
                mgv.invalidate();
            }
        });

        idBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color +=0.2f;
                mgv.invalidate();
            }
        });

        idDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color -=0.2f;
                mgv.invalidate();
            }
        });

        idGray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(satur==1) satur=0;
                else satur=1;
                mgv.invalidate();
            }
        });
    }
}
