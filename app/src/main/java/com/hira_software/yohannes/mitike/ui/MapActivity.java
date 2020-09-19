package com.hira_software.yohannes.mitike.ui;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hira_software.yohannes.mitike.R;
import com.hira_software.yohannes.mitike.database.LocationData;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public static double lat;
    public static double lang;
    public static String myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMaxZoomPreference(22f);
        mMap.setMinZoomPreference(15f);
        LatLng myLocationMap= new LatLng(lat,lang);
        mMap.addMarker(new MarkerOptions().position(myLocationMap).title(myLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocationMap));
    }
}