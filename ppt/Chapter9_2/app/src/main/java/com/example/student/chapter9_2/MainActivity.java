package com.example.student.chapter9_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final  static  int LINE = 1, CIRCLE = 2, NEMO=3;

    static  int curShape = LINE, sColor=Color.RED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
        setTitle("간단한 그림판");
    }

    private static class MyGraphicView extends View{
        int startX = -1, startY = -1, stopX = -1, stopY = -1;

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(10);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(sColor);

            switch (curShape){
                case LINE:
                    canvas.drawLine(startX, startY, stopX, stopY,paint);
                    break;
                case CIRCLE:
                    int radius = (int) Math.sqrt(Math.pow(stopX-startX,2)+Math.pow(stopY-startY,2));
                    canvas.drawCircle(startX,startY,radius,paint);
                    break;
                case NEMO:
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect,paint);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0,"선 그리기");
        menu.add(0, 2, 0,"원 그리기");
        menu.add(0, 3, 0,"사각형 그리기");

        SubMenu subMenu = menu.addSubMenu("색상 변경");
        subMenu.add(0, 4, 0, "빨강");
        subMenu.add(0, 5, 0, "초록");
        subMenu.add(0, 6, 0, "파랑");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                curShape = LINE;
                return true;
            case 2:
                curShape = CIRCLE;
                return true;
            case 3:
                curShape = NEMO;
                return true;
            case 4:
                sColor =Color.RED;
                return true;
            case 5:
                sColor =Color.GREEN;
                return true;
            case 6:
                sColor =Color.BLUE;
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
