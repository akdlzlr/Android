package com.example.student.chapter7_test05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    View toastView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(getApplicationContext());
                toastView = (View)View.inflate(MainActivity.this, R.layout.toast1,null);

                Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                int xOffset = (int) (Math.random()*display.getWidth());
                int yOffset = (int) (Math.random()*display.getHeight());
                toast.setGravity(Gravity.TOP|Gravity.LEFT,xOffset,yOffset);
                
                toast.setView(toastView);
                toast.show();
            }
        });
    }
}
