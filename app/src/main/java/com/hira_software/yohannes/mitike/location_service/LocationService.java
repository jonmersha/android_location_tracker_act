package com.hira_software.yohannes.mitike.location_service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LocationService extends Service {


    private static final String TAG = "MyLocationService";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class LocationListener implements android.location.LocationListener{
        Location location;

        public LocationListener(String provider) {

            Log.e(TAG, "LocationListener " + provider);
            location = new Location(provider);
        }

        @Override
        public void onLocationChanged(@NonNull Location location) {

        }
    }
}
