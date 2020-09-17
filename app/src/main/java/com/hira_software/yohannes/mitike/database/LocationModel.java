package com.hira_software.yohannes.mitike.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location_table")
public class LocationModel {
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name = "id")
   private int loc_id;

   @ColumnInfo(name="location_name")
   private String name;
   @ColumnInfo(name="description")
   private String description;
   @ColumnInfo(name="longitude")
   private double longitude;
   @ColumnInfo(name="latitude")
   private double latitude;
   @ColumnInfo(name="registration_time")
   private String registration_date;


   public int getLoc_id() {
      return loc_id;
   }

   public void setLoc_id(int loc_id) {
      this.loc_id = loc_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public double getLongitude() {
      return longitude;
   }

   public void setLongitude(double longitude) {
      this.longitude = longitude;
   }

   public double getLatitude() {
      return latitude;
   }

   public void setLatitude(double latitude) {
      this.latitude = latitude;
   }

   public String getRegistration_date() {
      return registration_date;
   }

   public void setRegistration_date(String registration_date) {
      this.registration_date = registration_date;
   }
}
