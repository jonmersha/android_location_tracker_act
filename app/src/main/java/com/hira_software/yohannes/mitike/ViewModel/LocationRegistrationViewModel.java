package com.hira_software.yohannes.mitike.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.hira_software.yohannes.mitike.repository.Repository;
import com.hira_software.yohannes.mitike.database.LocationModel;


public class LocationRegistrationViewModel extends AndroidViewModel {


   private Repository repository;


    public LocationRegistrationViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);

    }
    public  void insert(LocationModel locationModel){
        repository.registerLocation(locationModel);
    }


}
