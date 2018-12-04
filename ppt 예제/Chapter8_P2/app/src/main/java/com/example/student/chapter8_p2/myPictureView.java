package com.example.student.chapter8_p2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by student on 2018-11-07.
 */

public class myPictureView extends View{

    String imagePath = null;

    public myPictureView(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.scale(3,3,0,0);
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            int h = ((this.getHeight()/3)-bitmap.getHeight())/2;
            int w = ((this.getWidth()/3)-bitmap.getWidth())/2;

            canvas.drawBitmap(bitmap, w, h, null);
            bitmap.recycle();
        }
    }
}
