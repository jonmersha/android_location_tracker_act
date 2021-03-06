package com.hira_software.yohannes.mitike.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.hira_software.yohannes.mitike.R;
import com.hira_software.yohannes.mitike.ViewModel.LocationUpdateViewModel;
import com.hira_software.yohannes.mitike.ViewModel.LocationRegistrationViewModel;
import com.hira_software.yohannes.mitike.database.LocationModel;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private double longitude;
    private double latitude;

    private String locationProvider;


    private LocationManager locationManager;

    Location location;


    EditText locationName;
    EditText locationDescription;


    Criteria criteria;

    private LocationRegistrationViewModel registrationViewModel;
    LocationUpdateViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationName = findViewById(R.id.location_name_edit);
        locationDescription = findViewById(R.id.location_description);

        locationSetting();
        registrationViewModel=new ViewModelProvider(this)
                .get(LocationRegistrationViewModel.class);

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, "Location Provider  Disabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        locationSetting();

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.latitude=location.getLatitude();
        this.longitude=location.getLongitude();
    }

    public void showOnMap(View view) {

        MapActivity.lat=this.latitude;
        MapActivity.lang=this.longitude;
        MapActivity.myLocation="CurrentLocation";

        Intent intent=new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void listLocation(View view) {

        finish();
        Intent locationList=new Intent(this, LocationListActivity.class);
        startActivity(locationList);
    }

    public void saveLocation(View view) {


        String systemDatetime= new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault()).format(new Date());

        LocationModel locationEntity=new LocationModel();
        if((locationName.getText().toString().length()<2)||locationDescription.getText().toString().length()<1){
            Toast.makeText(this, "Location Name or Location desc empty ", Toast.LENGTH_SHORT).show();
        }
        else{
        locationEntity.setName(locationName.getText().toString());
        locationEntity.setDescription(locationDescription.getText().toString());
        locationEntity.setLatitude(this.latitude);
        locationEntity.setLongitude(this.longitude);
        locationEntity.setRegistration_date(systemDatetime);
        registrationViewModel.insert(locationEntity);

        //location
            Intent intent=new Intent(this,LocationListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationSetting();

    }


    public void locationSetting(){
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        criteria = new Criteria();
        this.locationProvider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                        99);
            }
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        locationManager.requestLocationUpdates(locationProvider,400,10,this);
        if (location != null) {
            Toast.makeText(this,  locationProvider+"has been selected", Toast.LENGTH_SHORT).show();
            onLocationChanged(location);
        } else {
            Toast.makeText(this, "Location not enabled", Toast.LENGTH_SHORT).show();
}

    }
}