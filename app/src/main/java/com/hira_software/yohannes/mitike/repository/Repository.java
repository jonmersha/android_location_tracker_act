package com.hira_software.yohannes.mitike.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.hira_software.yohannes.mitike.database.DataBase;
import com.hira_software.yohannes.mitike.database.LocationModel;
import com.hira_software.yohannes.mitike.database.LocationDataAccessObject;

import java.util.List;

public  class Repository {
    private LocationDataAccessObject myLocationDAO;
    LiveData<List<LocationModel>> allLocation;
    LiveData<List<LocationModel>> filterLocation;


    public Repository(Application application) {
        DataBase locationDataBase= DataBase.getDatabaseInstance(application);
        this.myLocationDAO=locationDataBase.myLocationDataAccessObject();
    }
    public void registerLocation(LocationModel entity){

        DataBase.databaseWriteExecutor.execute(()->{
            myLocationDAO.registerLocation(entity);
        });


    }
    public void updateLocation(LocationModel entity){
        DataBase.databaseWriteExecutor.execute(()->{

            myLocationDAO.updateLocation(entity);
        });

    }

    public LiveData<List<LocationModel>> getAllLocation() {
        allLocation=myLocationDAO.getAllLocation();
        return allLocation;
    }

    public void deleteLocation(LocationModel locationModel){
        DataBase.databaseWriteExecutor.execute(()-> {
            myLocationDAO.deleteLocation(locationModel);
        });
    }

    public LiveData<List<LocationModel>> getFilterLocation(String searchKey) {
        filterLocation=myLocationDAO.getLocations(searchKey);
        return filterLocation;
    }


}
