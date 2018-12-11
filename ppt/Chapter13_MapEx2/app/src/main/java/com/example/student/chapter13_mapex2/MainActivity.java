package com.example.student.chapter13_mapex2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;


public class MainActivity extends NMapActivity {
    NMapView mMapView;
    NMapController mMapController;
    private SharedPreferences mPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // create map view
        mMapView = new NMapView(this);

// set Client ID for Open MapViewer Library
        mMapView.setNcpClientId("cslbj0kypq");

// set the activity content to the map view
        setContentView(mMapView);

// initialize map view
        mMapView.setClickable(true);

// register listener for map state changes
        mMapView.setOnMapStateChangeListener(onMapViewStateChangeListener);
        mMapView.setOnMapViewTouchEventListener(onMapViewTouchEventListener);

// use map controller to zoom in/out, pan and set map center, zoom level etc.
        mMapController = mMapView.getMapController();

        // use built in zoom controls
        mMapView.setBuiltInZoomControls(true, null);
    }

    public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {
        if (errorInfo == null) { // success
            mMapController.setMapCenter(new NGeoPoint(126.978371, 37.5666091), 11);
        } else { // fail
            Log.e("LOG_TAG", "onMapInitHandler: error=" + errorInfo.toString());
        }
    }



    private final NMapView.OnMapStateChangeListener onMapViewStateChangeListener = new NMapView.OnMapStateChangeListener() {

        @Override
        public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {

//            if (errorInfo == null) { // success
//                // restore map view state such as map center position and zoom level.
//                restoreInstanceState();
//
//            } else { // fail
//                //Log.e(LOG_TAG, "onFailedToInitializeWithError: " + errorInfo.toString());
//
//                //Toast.makeText(NMapViewer.this, errorInfo.toString(), Toast.LENGTH_LONG).show();
//            }
        }

        @Override
        public void onAnimationStateChange(NMapView mapView, int animType, int animState) {
//            if (DEBUG) {
//                Log.i(LOG_TAG, "onAnimationStateChange: animType=" + animType + ", animState=" + animState);
//            }
        }

        @Override
        public void onMapCenterChange(NMapView mapView, NGeoPoint center) {
//            if (DEBUG) {
//                Log.i(LOG_TAG, "onMapCenterChange: center=" + center.toString());
//            }
        }

        @Override
        public void onZoomLevelChange(NMapView mapView, int level) {
//            if (DEBUG) {
//                Log.i(LOG_TAG, "onZoomLevelChange: level=" + level);
//            }
        }

        @Override
        public void onMapCenterChangeFine(NMapView mapView) {

        }
    };

    private final NMapView.OnMapViewTouchEventListener onMapViewTouchEventListener = new NMapView.OnMapViewTouchEventListener() {

        @Override
        public void onLongPress(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLongPressCanceled(NMapView mapView) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSingleTapUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTouchDown(NMapView mapView, MotionEvent ev) {

        }

        @Override
        public void onScroll(NMapView mapView, MotionEvent e1, MotionEvent e2) {
        }

        @Override
        public void onTouchUp(NMapView mapView, MotionEvent ev) {
            // TODO Auto-generated method stub

        }

    };
}

