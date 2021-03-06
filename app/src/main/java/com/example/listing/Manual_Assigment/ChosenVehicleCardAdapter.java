package com.example.listing.Manual_Assigment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listing.AssignDialog_Configured.ConfiguredLoaderListAdapter;
import com.example.listing.R;
import com.example.listing.databinding.ChosenVehicleCardItemBinding;
import com.example.listing.databinding.ChosenVehicleCardItemVertBinding;
import com.example.listing.models.Vehicle;

import java.util.ArrayList;

public class ChosenVehicleCardAdapter extends RecyclerView.Adapter<ChosenVehicleCardAdapter.ViewHolder> {
    ArrayList<Vehicle> Vehicle;
    Context context;

    public ChosenVehicleCardAdapter(ArrayList<Vehicle> Vehicle) {
        this.Vehicle = Vehicle;
    }

    @NonNull
    @Override
    public ChosenVehicleCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChosenVehicleCardItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.chosen_vehicle_card_item, parent, false);

//        ChosenVehicleCardItemVertBinding binding = DataBindingUtil.inflate(
//                LayoutInflater.from(parent.getContext()),
//                R.layout.chosen_vehicle_card_item_vert, parent, false);


        return new ChosenVehicleCardAdapter.ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull ChosenVehicleCardAdapter.ViewHolder holder, int position) {
        Vehicle vehicle = Vehicle.get(position);
        holder.bind(vehicle);

        ConfiguredLoaderListAdapter configuredLoaderListAdapter = new ConfiguredLoaderListAdapter(vehicle.getLoaders());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.itemRowBinding.loadersListCard.setLayoutManager(linearLayoutManager);
        holder.itemRowBinding.loadersListCard.setAdapter(configuredLoaderListAdapter);

//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
//        holder.itemVertBinding.loadersListCardVert.setLayoutManager(linearLayoutManager1);
//        holder.itemVertBinding.loadersListCardVert.setAdapter(configuredLoaderListAdapter);


    }

    @Override
    public int getItemCount() {
        return Vehicle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ChosenVehicleCardItemBinding itemRowBinding;
        public ChosenVehicleCardItemVertBinding itemVertBinding;
        public TextView chosenDriverName;
        public RecyclerView loadersList;

        public ViewHolder(@NonNull ChosenVehicleCardItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
            chosenDriverName = itemRowBinding.getRoot().findViewById(R.id.chosen_vehicles_card_tv);
            loadersList = itemRowBinding.loadersListCard;
        }

        public ViewHolder(ChosenVehicleCardItemVertBinding itemVertBinding) {
            super(itemVertBinding.getRoot());
            this.itemVertBinding = itemVertBinding;
            chosenDriverName = itemVertBinding.textView6;
            loadersList = itemVertBinding.loadersListCardVert;
        }

        public void bind(Vehicle vehicle) {
            itemRowBinding.setChosenDriver(vehicle);
            itemRowBinding.executePendingBindings();
//
//            itemVertBinding.setChosenDriver(vehicle);
//            itemVertBinding.executePendingBindings();
        }
    }
}
