package com.example.student.doit11_4_mylocationwidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.List;

/**
 * Created by student on 2018-11-27.
 */

public class MyLocationProvider extends AppWidgetProvider {

    public static double ycoord = 0.0D;
    public static double xcoord = 0.0D;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Log.d("MyLocationProvider", "onUpdate() called : " + ycoord + ", " + xcoord);

        final int size = appWidgetIds.length;
        Log.d("MyLocationProvider","appWidgetIds.length : "+size);
        for(int i=0;i<size;i++) {
            int appWidgetId = appWidgetIds[i];

            String uriBegin = "geo:" + ycoord + "," + xcoord;
            String query = ycoord + ", " + xcoord + "(내 위치)";
            String encodeQuery = Uri.encode(query);
            String UriString = uriBegin + "?q=" + encodeQuery + "&z=15";
            Uri uri = Uri.parse(UriString);
            Log.d("MyLocationProvider", uri.toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.mylocation);

            views.setOnClickPendingIntent(R.id.txtInfo, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        Log.d("MyLocationProvider", "for문 종료");
        context.startService(new Intent(context,GPSLocationService.class));
    }

    public static class GPSLocationService extends Service{
        public static final String TAG = "GPSLocationService";

        private LocationManager manager = null;

        private LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG, "onLocationChanged() called.");

                updateCoordinates(location.getLatitude(), location.getLongitude());

                stopSelf();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            Log.d(TAG, "onCreate() called.");
            manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startListening();
            Log.d(TAG, "onStartCommand() called.");
            return super.onStartCommand(intent, flags, startId);
        }

        @Override
        public void onDestroy() {
            stopListening();
            Log.d(TAG, "onDestroy() called.");
            super.onDestroy();
        }

        private void startListening(){
            Log.d(TAG, "startListening() called.");

            final Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(Criteria.POWER_LOW);

            final String bestProvider = manager.getBestProvider(criteria, true);

            if (bestProvider != null && bestProvider.length() > 0) {
                try {
                    manager.requestLocationUpdates(bestProvider, 500, 10, listener);
                }catch (SecurityException e){

                }
            } else {
                final List<String> providers = manager.getProviders(true);

                for (final String provider : providers) {
                    try {
                        manager.requestLocationUpdates(provider, 500, 10, listener);
                    }catch (SecurityException e){

                    }
                }
            }
        }

        private void stopListening(){
            try {
                if (manager != null && listener != null) {
                    manager.removeUpdates(listener);
                }

                manager = null;
            } catch (final Exception ex) {

            }
        }

        private void updateCoordinates(double latitude, double longitude){
            Geocoder geocoder = new Geocoder(this);

            List<Address> addresses = null;
            String info = "";

            Log.d(TAG, "updateCoordinates() 호출됨");

            try{
                addresses = geocoder.getFromLocation(latitude, longitude,2);

                if(addresses!=null&&addresses.size()>0){
                    int addressCount = addresses.get(0).getMaxAddressLineIndex();
                    if(addressCount!=-1){
                        for(int idx=0; idx<=addressCount;++idx){
                            info += addresses.get(0).getAddressLine(idx);
                            if (idx < addressCount)
                                info += ", ";
                        }
                    }else {
                        info +=addresses.get(0).getFeatureName()+", "
                                +addresses.get(0).getSubAdminArea()+", "
                                + addresses.get(0).getAdminArea();
                    }
                }
                Log.d(TAG, "Address : "+addresses.get(0).toString());
            }catch (Exception e){
                e.printStackTrace();
            }

            if (info.length() <= 0) {
                info = "[내 위치] " + latitude + ", " + longitude
                        + "\n터치하면 지도로 볼 수 있습니다.";
            } else {
                info += ("\n" + "[내 위치] " + latitude + ", " + longitude + ")");
                info += "\n터치하면 지도로 볼 수 있습니다.";
            }

            RemoteViews views = new RemoteViews(getPackageName(), R.layout.mylocation);

            views.setTextViewText(R.id.txtInfo, info);

            ComponentName thisWidget = new ComponentName(this, MyLocationProvider.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(this);
            manager.updateAppWidget(thisWidget, views);

            xcoord = longitude;
            ycoord = latitude;
            Log.d(TAG, "coordinates : " + xcoord + ", " + ycoord);
        }
    }
}
