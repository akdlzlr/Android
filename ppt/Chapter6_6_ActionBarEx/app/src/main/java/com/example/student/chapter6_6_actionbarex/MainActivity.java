package com.example.student.chapter6_6_actionbarex;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ActionBar.Tab tabSong, tabArtist, tabAlbum;
    MyTabFragment myFrags[] = new MyTabFragment[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();

        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabSong = bar.newTab();
        tabSong.setText("음악별");
        tabSong.setTabListener(this);
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);
        bar.addTab(tabAlbum);

    }



    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        MyTabFragment myTabFrag = null;

        if(myFrags[tab.getPosition()]==null) {
            myTabFrag = new MyTabFragment();

            Bundle data = new Bundle();
            data.putString("tabName", tab.getText().toString());

            myTabFrag.setArguments(data);
            myFrags[tab.getPosition()] = myTabFrag;
            // 선택한 탭이 기존에 한번도 선택된 적이 없다면 프래그먼트(화면)를 생성해서
            // 선언한 배열의 해당 위치에 넣는다.
        }else {
            myTabFrag = myFrags[tab.getPosition()];
        }

        ft.replace(android.R.id.content, myTabFrag);
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


    // 프래그먼트 만들기
    static public class MyTabFragment extends Fragment{

        String tabName;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");

            // 3개의 탭을 클릭할 때마다 다른 프래그먼트(작은화면)가 나오도록, 탭을 클릭할 때 지정한 데이터로
            // 각 프래그먼트를 지정한다.
            // 여기서는 탭의 텍스트인 '음악별,가수별,앨범별'로 지정한다.

        }

        // 프래그먼트에 나타날 화면을 구성, 최종적으로 구성한 화면(View)을 반환한다.



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            LinearLayout layout = new LinearLayout(super.getActivity());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(params);

            if (tabName=="음악별") {layout.setBackgroundColor(Color.RED);}
            if (tabName=="가수별") {layout.setBackgroundColor(Color.BLUE);}
            if (tabName=="앨범별") {layout.setBackgroundColor(Color.GREEN);}

            return layout;
        }


    }

}
