package com.example.student.chapter9_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

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
            paint.setColor(Color.BLACK);
            paint.setStrokeWidth(100);

            paint.setAntiAlias(true);

            canvas.drawLine(100,100,500,100,paint);

            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setColor(Color.BLACK);
            canvas.drawLine(100, 300, 500, 300, paint);

            RectF rectF = new RectF();
            rectF.set(60, 500, 60+200,500+100);
            canvas.drawOval(rectF, paint);

            rectF.set(60, 600, 60+200,600+100);
            canvas.drawArc(rectF, 45,90,true,paint);

            paint.setColor(Color.BLUE);
            Rect rect = new Rect();
            rect.set(60, 800, 60+200, 800+200);
            canvas.drawRect(rect,paint);

            paint.setColor(Color.argb(0x88, 0xff, 0x00, 0x00));
            rect.set(60+30, 800+30, 60+200+30, 800+200+30);
            canvas.drawRect(rect,paint);
        }

        // MyGraphicView 클래스가 생성되거나 무효화(invalidate)되면 호출되는 메소드이다.
        // 일반적으로 화면에 그려질 내용을 이곳에 코딩한다.

    }

}
