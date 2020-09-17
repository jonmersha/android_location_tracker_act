package com.hira_software.yohannes.mitike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.hira_software.yohannes.mitike.R;
import com.hira_software.yohannes.mitike.ViewModel.EditLocationViewModel;
import com.hira_software.yohannes.mitike.database.LocationModel;
import com.hira_software.yohannes.mitike.database.LocationData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditLocationActivity extends AppCompatActivity {

    EditText locationName;
    EditText locationDescription;

    TextView longitude;
    TextView latitude;
    LocationModel location;

    EditLocationViewModel editLocationViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_location);

        locationName=findViewById(R.id.location_name_edit);
        locationDescription=findViewById(R.id.location_description);

        longitude=findViewById(R.id.longitude_data);
        latitude=findViewById(R.id.latitude_data);
         location= LocationData.locationModel;

         locationName.setText(location.getName());
         locationDescription.setText(location.getDescription());
         longitude.setText(""+location.getLongitude());
         latitude.setText(""+location.getLatitude());

        editLocationViewModel=new ViewModelProvider(this).get(EditLocationViewModel.class);

    }

    public void backToList(View view) {
        finish();
    }

    public void showOnMap(View view) {
        MapActivity.lang=location.getLongitude();
        MapActivity.lat=location.getLatitude();
        MapActivity.myLocation=location.getName();
        Intent intent=new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void updateLocationInfo(View view) {

        String systemDatetime= new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault()).format(new Date());

        location.setName(locationName.getText().toString());
        location.setDescription(locationDescription.getText().toString());
        location.setRegistration_date(systemDatetime);
        editLocationViewModel.update(location);
        fileList();
    }

    public void deleteLocation(View view) {
        editLocationViewModel.delete(location);
        finish();
    }
}