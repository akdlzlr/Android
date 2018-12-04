package com.example.student.chapter9_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }

    private static class MyGraphicView extends View{

        public MyGraphicView(Context c){
            super(c);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);

            Paint paint = new Paint();

            paint.setAntiAlias(true);

            paint.setColor(Color.RED);
            canvas.drawLine(10,10,1000,10,paint);

            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            canvas.drawLine(10, 30, 1000, 30, paint);

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            Rect rect = new Rect(10,50,10+100,50+100);
            canvas.drawRect(rect,paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect rect2 = new Rect(130,50,130+100,50+100);
            canvas.drawRect(rect2,paint);

            RectF rectF = new RectF(250,50,250+100,50+100);
            canvas.drawRoundRect(rectF,20,20,paint);

            canvas.drawCircle(60,220,50,paint);

            Path path = new Path();
            paint.setStyle(Paint.Style.STROKE);
            path.moveTo(10,290);
            path.lineTo(10+100,290+100);
            path.lineTo(10+200,290);
            path.lineTo(10+300,290+100);
            path.lineTo(10+400,290);
            path.lineTo(10+500,290+100);
            canvas.drawPath(path,paint);

            paint.setTextSize(200);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawText("안드로이드", 80, 1000, paint);

        }
    }

}
