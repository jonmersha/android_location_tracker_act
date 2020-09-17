package com.hira_software.yohannes.mitike.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.hira_software.yohannes.mitike.repository.Repository;
import com.hira_software.yohannes.mitike.database.LocationModel;

import java.util.List;

public class LocationListViewModel extends AndroidViewModel {


    Repository repository;
    private LiveData<List<LocationModel>> locationList;
    private LiveData<List<LocationModel>> filterList;

    public LocationListViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }

    public LiveData<List<LocationModel>> getLocationList() {
        locationList=repository.getAllLocation();
        return locationList;
    }

    public LiveData<List<LocationModel>> getFilterList(String searchKey) {
        filterList=repository.getFilterLocation(searchKey);
        return filterList;
    }
}
