package com.example.student.chapter7_2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout baseLayout;
    Button btn1, btn2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseLayout = (LinearLayout)findViewById(R.id.baseLayout);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        registerForContextMenu(btn1);
        registerForContextMenu(btn2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();

        if(v==btn1){
            menu.setHeaderTitle("배경색 변경");
            //menuInflater.inflate(R.menu.menu1, menu);
            menu.add(0,1,0,"배경색 (빨강)");
            menu.add(0,2,0,"배경색 (파랑)");
            menu.add(0,3,0,"배경색 (초록)");
            menu.add(0,4,0,"초기화");
        }
        if(v==btn2){
            menu.setHeaderTitle("버튼 변경");
            //menuInflater.inflate(R.menu.menu2,menu);
            menu.add(0,5,0,"회전");
            menu.add(0,6,0,"확대");
            menu.add(0,7,0,"초기화");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //case R.id.red:
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            //case R.id.blue:
            case 2:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            //case R.id.green:
            case 3:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            //case R.id.white:
            case 4:
                baseLayout.setBackgroundColor(Color.WHITE);
                return true;
            //case R.id.subrotate:
            case 5:
                btn2.setRotation(45);
                return true;
            //case R.id.subsize:
            case 6:
                btn2.setScaleX(2);
                return true;
            //case R.id.back:
            case 7:
                btn2.setRotation(0);
                btn2.setScaleX(1);
                return true;
        }



        return false;
    }
}
