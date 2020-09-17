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
import com.hira_software.yohannes.mitike.ViewModel.EditLocationViewModel;
import com.hira_software.yohannes.mitike.ViewModel.RegistrationViewModel;
import com.hira_software.yohannes.mitike.database.LocationModel;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private double longitude;
    private double latitude;

    private String locationProvider;


    private LocationManager locationManager;


    EditText locationName;
    EditText locationDescription;

    Button saveLocation;
    Button viewLocation;
    Button viewMapActivity;

    private RegistrationViewModel registrationViewModel;
    EditLocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationName = findViewById(R.id.location_name_edit);
        locationDescription = findViewById(R.id.location_description);

        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        this.locationProvider = locationManager.getBestProvider(criteria, false);

        registrationViewModel=new ViewModelProvider(this)
                .get(RegistrationViewModel.class);

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

        if (location != null) {
            System.out.println("Provider " + locationDescription + " has been selected.");
            onLocationChanged(location);
        } else {
            Toast.makeText(this, "Location not enabled", Toast.LENGTH_SHORT).show();
        }


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

        Intent locationList=new Intent(this, LocationList.class);
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

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {

            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                        99);
            }

            return;
        }
        locationManager.requestLocationUpdates(locationProvider,400,10,this);
    }
}