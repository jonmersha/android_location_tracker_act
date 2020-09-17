package com.hira_software.yohannes.mitike.adaptors;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.hira_software.yohannes.mitike.R;
import com.hira_software.yohannes.mitike.database.LocationModel;
import com.hira_software.yohannes.mitike.database.LocationData;
import com.hira_software.yohannes.mitike.ui.EditLocationActivity;

import java.util.ArrayList;
import java.util.List;

public class LocationAdaptor extends RecyclerView.Adapter<LocationAdaptor.LocationList> {

    List<LocationModel> locationModelList =new ArrayList<>();


    public void setLocationModelList(List<LocationModel> locationModelList) {
        this.locationModelList = locationModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_component,parent,false);
        return new LocationList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationList holder, int position) {

        LocationModel locationModel = locationModelList.get(position);

        holder.location_id= locationModel.getLoc_id();

        holder.locationName= locationModel.getName();
        holder.description= locationModel.getDescription();
        holder.latitude= locationModel.getLatitude();


        holder.longitude= locationModel.getLongitude();
        holder.time= locationModel.getRegistration_date();


        holder.locationNameView.setText(locationModel.getName());
        holder.descriptionView.setText(locationModel.getDescription());
        holder.reg_timeView.setText(locationModel.getRegistration_date());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(), EditLocationActivity.class);
                LocationData.locationModel = locationModel;
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return locationModelList.size();
    }

    public class LocationList extends RecyclerView.ViewHolder {

        TextView locationNameView;
        TextView descriptionView;
        TextView reg_timeView;

        public String locationName;
        public String description;
        public double latitude;
        public double longitude;

        public CardView cardView;

        public String time;
        private  int location_id;

        public LocationList(@NonNull View itemView) {

            super(itemView);
            cardView=itemView.findViewById(R.id.card);
            locationNameView =itemView.findViewById(R.id.location_name_edit);
            descriptionView=itemView.findViewById(R.id.location_description);
            reg_timeView= itemView.findViewById(R.id.capture_time);
        }
    }
}
