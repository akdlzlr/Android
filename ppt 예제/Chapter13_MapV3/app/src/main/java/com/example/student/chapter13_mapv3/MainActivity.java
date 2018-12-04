package com.example.student.chapter13_mapv3;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraAnimation;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.GroundOverlay;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    NaverMap nm;
    Marker marker = new Marker();
    Marker marker2 = new Marker();
    GroundOverlay go;
    ArrayList<GroundOverlay> ago = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        final InfoWindow infoWindow = new InfoWindow();

        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "정보 창 내용을 입력하세요.";
            }
        });

        final InfoWindow infoWindow2 = new InfoWindow();

        infoWindow2.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "정보 창 내용을 입력하세요.2";
            }
        });

        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                if (marker.getInfoWindow() == null) {
                    // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                    infoWindow.open(marker);
                } else {
                    // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                    infoWindow.close();
                }
                return true;
            }
        });

        marker2.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                if (marker2.getInfoWindow() == null) {
                    // 현재 마커에 정보 창이 열려있지 않을 경우 엶
                    infoWindow2.open(marker2);
                } else {
                    // 이미 현재 마커에 정보 창이 열려있을 경우 닫음
                    infoWindow2.close();
                }
                return true;
            }
        });
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        nm = naverMap;
        nm.getUiSettings().setZoomControlEnabled(true);
        nm.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                LatLng laglng2 = new LatLng(latLng.latitude + 0.0005, latLng.longitude + 0.0005);
                go = new GroundOverlay();
                go.setImage(OverlayImage.fromResource(R.drawable.presence_video_busy));
                go.setBounds(new LatLngBounds(latLng, laglng2));

                ago.add(go);

                go.setMap(nm);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "기본지도");
        menu.add(0, 2, 0, "내비게이션 지도");
        menu.add(0, 3, 0, "위성 지도");
        menu.add(0, 4, 0, "하이브리드지도");
        menu.add(0, 5, 0, "남산타워");
        menu.add(0, 6, 0, "마커1 생성");
        menu.add(0, 7, 0, "마커2 생성");
        menu.add(0, 8, 0, "마커 삭제");
        menu.add(0, 9, 0, "마커 하나씩 삭제");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                nm.setMapType(NaverMap.MapType.Basic);
                return true;
            case 2:
                nm.setMapType(NaverMap.MapType.Navi);
                return true;
            case 3:
                nm.setMapType(NaverMap.MapType.Satellite);
                return true;
            case 4:
                nm.setMapType(NaverMap.MapType.Hybrid);
                return true;
            case 5:
                nm.moveCamera(CameraUpdate.scrollTo(new LatLng(37.551035, 126.990908)).animate(CameraAnimation.Easing));
                return true;
            case 6:

                marker.setPosition(new LatLng(37.5666000, 126.9783740));
                marker.setWidth(80);
                marker.setHeight(100);
                marker.setMap(nm);
                return true;
            case 7:

                marker2.setPosition(new LatLng(37.5666000, 126.9780000));
                marker2.setWidth(80);
                marker2.setHeight(100);
                marker2.setMap(nm);
                return true;
            case 8:
                marker.setMap(null);
                marker2.setMap(null);
                android.util.Log.i("ago 사이즈", "사이즈 : " + ago.size());
                for (int i = ago.size() - 1; i >= 0; i--) {
                    ago.remove(i).setMap(null);
                }
                return true;
            case 9:
                if (ago.size() != 0) {
                    ago.remove(ago.size() - 1).setMap(null);
                }
                return true;
        }
        return false;
    }
}
