package com.example.student.doit05_2_bitmapwidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by student on 2018-11-20.
 */

public class BitmapButton extends AppCompatButton {

    int iconNomal = R.drawable.bitmap_button_normal;
    int iconClicked = R.drawable.bitmap_button_clicked;

    int iconStatus = STATUS_NORMAL;
    public static int STATUS_NORMAL=0;
    public static  int STATUS_CLICKED=1;

    public BitmapButton(Context context) {
        super(context);
        init();
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init(){
        setBackgroundResource(iconNomal);

        int defaultTC = Color.WHITE;
        float defaultTS = getResources().getDimension(R.dimen.text_size);
        Typeface defaultTypeface  = Typeface.DEFAULT_BOLD;

        setTextColor(defaultTC);
        setTextSize(defaultTS);
        setTypeface(defaultTypeface);

    }

    public void setIcon(int iconNormal, int iconClicked){
        this.iconNomal = iconNormal;
        this.iconClicked = iconClicked;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(this.iconClicked);
                iconStatus = STATUS_CLICKED;
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundResource(this.iconNomal);

                iconStatus = STATUS_NORMAL;

                break;
        }

        invalidate();

        return true;
    }
}
