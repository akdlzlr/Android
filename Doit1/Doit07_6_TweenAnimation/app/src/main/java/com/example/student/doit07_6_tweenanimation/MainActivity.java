package com.example.student.doit07_6_tweenanimation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView skyImage,swingImage,waterImage;
    Animation shakeAnimation, flowAnimation, dropAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swingImage = (ImageView) findViewById(R.id.swingImage);
        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
        swingImage.setAnimation(shakeAnimation);

        waterImage = (ImageView) findViewById(R.id.waterImage);
        dropAnimation = AnimationUtils.loadAnimation(this, R.anim.drop);
        waterImage.setAnimation(dropAnimation);

        skyImage = (ImageView) findViewById(R.id.skyImage);
        flowAnimation = AnimationUtils.loadAnimation(this, R.anim.flow);
        skyImage.setAnimation(flowAnimation);

        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.sky_background);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        ViewGroup.LayoutParams params = skyImage.getLayoutParams();
        params.width = bitmapWidth;
        params.height = bitmapHeight;

        skyImage.setImageBitmap(bitmap);

        flowAnimation.setAnimationListener(new AnimationAdapter());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);



        if (hasFocus) {
            shakeAnimation.start();
            dropAnimation.start();
            flowAnimation.start();
        } else {
            shakeAnimation.reset();
            dropAnimation.reset();
            flowAnimation.reset();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

    }



    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }
    private final class AnimationAdapter implements Animation.AnimationListener {

        public void onAnimationStart(Animation animation) {

        }

        public void onAnimationEnd(Animation animation) {

        }

        public void onAnimationRepeat(Animation animation) {

        }

    }
}
