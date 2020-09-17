package com.hira_software.yohannes.mitike.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDataAccessObject {
    @Insert
    void registerLocation(LocationModel locationModel);

    @Update
    void updateLocation(LocationModel entity);

    @Query("SELECT * FROM location_table")
    LiveData<List<LocationModel>> getAllLocation();
    @Delete
    void deleteLocation(LocationModel locationModel);
    @Query("SELECT * FROM location_table where location_name like :searchKey||'%' or description like:searchKey||'%'")
    LiveData<List<LocationModel>> getLocations(String searchKey);
}
