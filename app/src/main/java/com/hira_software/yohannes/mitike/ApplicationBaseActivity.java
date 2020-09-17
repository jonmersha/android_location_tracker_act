package com.hira_software.yohannes.mitike;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.hira_software.yohannes.mitike.database.LocationData;
import com.hira_software.yohannes.mitike.location_service.LocationService;
import com.hira_software.yohannes.mitike.ui.MainActivity;

public class ApplicationBaseActivity extends Application{




    @Override
    public void onCreate() {
        super.onCreate();



    }


}
