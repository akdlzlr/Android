package com.example.student.chapter6_5_taphost;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    // 아이스크림 샌드위치 이후에서 탭 액티비티를 사용하면 경고가 나오는 것을 막아주기 위해
    // @SuppressWarnings("deprecation")를 사용

    // 상속받는 클래스는 TabActivity 이여야 한다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecSong = tabHost.newTabSpec("Song").setIndicator("음악별");
        tabSpecSong.setContent(R.id.tabsong);
        tabHost.addTab(tabSpecSong);

        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("Artist").setIndicator("가수별");
        tabSpecArtist.setContent(R.id.tabartist);
        tabHost.addTab(tabSpecArtist);

        TabHost.TabSpec tabSpecAlbum = tabHost.newTabSpec("Album ").setIndicator("앨범별");
        tabSpecAlbum.setContent(R.id.tabalbum);
        tabHost.addTab(tabSpecAlbum);

        tabHost.setCurrentTab(0);
    }
}
