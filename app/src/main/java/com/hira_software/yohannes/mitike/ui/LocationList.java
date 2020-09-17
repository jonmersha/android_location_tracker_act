package com.hira_software.yohannes.mitike.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hira_software.yohannes.mitike.adaptors.LocationAdaptor;
import com.hira_software.yohannes.mitike.R;
import com.hira_software.yohannes.mitike.ViewModel.ListViewModel;
import com.hira_software.yohannes.mitike.database.LocationModel;

import java.util.List;

public class LocationList extends AppCompatActivity {

    RecyclerView listOfLocation;
    LocationAdaptor locationAdaptor;
    ListViewModel listViewModel;

    EditText searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        listOfLocation=findViewById(R.id.location_list);

        searchKey=findViewById(R.id.search_key);

        searchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(ViewLocation.this, "textChanged", Toast.LENGTH_SHORT).show();
                listViewModel.getFilterList(charSequence.toString()).observe(LocationList.this, new Observer<List<LocationModel>>() {
                    @Override
                    public void onChanged(List<LocationModel> locationEntities) {
                        locationAdaptor.setLocationModelList(locationEntities);
                    }
                });


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


        listOfLocation.setLayoutManager(new LinearLayoutManager(this));
        listOfLocation.setHasFixedSize(true);
        locationAdaptor =new LocationAdaptor();
        listOfLocation.setAdapter(locationAdaptor);

        listViewModel=new ViewModelProvider(this).get(ListViewModel.class);

        listViewModel.getLocationList().observe(this, new Observer<List<LocationModel>>() {
            @Override
            public void onChanged(List<LocationModel> locationEntities) {
                locationAdaptor.setLocationModelList(locationEntities);
            }
        });

    }
}