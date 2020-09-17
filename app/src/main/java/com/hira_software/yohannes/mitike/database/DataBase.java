package com.hira_software.yohannes.mitike.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Database(entities = {LocationModel.class},version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract LocationDataAccessObject myLocationDataAccessObject();

    public static   volatile DataBase DATABASE_INSTANCE;
    private static final int NUMBER_OF_THREADS=4;

   public  static final Executor databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

   public  static DataBase getDatabaseInstance(final Context context){

        if(DATABASE_INSTANCE==null){
            synchronized (DataBase.class){
                if(DATABASE_INSTANCE==null){
                    DATABASE_INSTANCE= Room.databaseBuilder(
                            context.getApplicationContext(),
                            DataBase.class,"location_database")
                            .build();
                }
            }
        }
        return DATABASE_INSTANCE;
    }
}
