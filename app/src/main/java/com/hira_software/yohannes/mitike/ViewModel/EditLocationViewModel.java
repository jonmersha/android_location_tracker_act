package com.hira_software.yohannes.mitike.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.hira_software.yohannes.mitike.repository.Repository;
import com.hira_software.yohannes.mitike.database.LocationModel;

import java.util.List;

public class EditLocationViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<LocationModel>> allLocation;



    public EditLocationViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        allLocation=repository.getAllLocation();
    }

    public  void update(LocationModel locationModel){
        repository.updateLocation(locationModel);
    }

    public  void delete(LocationModel locationModel){
        repository.deleteLocation(locationModel);
    }

}
