package com.example.student.chapter7_test04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView)findViewById(R.id.iv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0,1,0,"강이지");
        menu.add(0,2,0,"고양이");
        menu.add(0,3,0,"토끼");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1:
                iv.setImageResource(R.drawable.dog);
                return true;
            case 2:
                iv.setImageResource(R.drawable.cat);
                return true;
            case 3:
                iv.setImageResource(R.drawable.rabbit);
                return true;

        }


        return false;
    }
}
