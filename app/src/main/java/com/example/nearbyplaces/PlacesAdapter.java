package com.example.nearbyplaces;

import static android.service.controls.ControlsProviderService.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlaceItemViewHolder> {
    private final List<HashMap<String, String>> nearbyPlacesList;
    public PlacesAdapter(List<HashMap<String, String>> nearbyPlacesList){
        this.nearbyPlacesList = nearbyPlacesList;
    }
    @NonNull
    @Override
    public PlaceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item, parent, false);
        return new PlaceItemViewHolder(itemView);
    }

    @Override
    // get the position of the place
    public void onBindViewHolder(@NonNull PlaceItemViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder() is called at position: "+ position);
        HashMap<String, String> place = nearbyPlacesList.get(position);
        holder.populatePlace(place);
    }

    @Override
    //get the number of the item to make sure we cover all the places we got
    public int getItemCount() {
        return nearbyPlacesList.size();
    }
}
