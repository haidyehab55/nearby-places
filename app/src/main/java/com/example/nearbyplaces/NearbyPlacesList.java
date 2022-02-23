package com.example.nearbyplaces;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.List;

public class NearbyPlacesList extends AppCompatActivity {
    private List<HashMap<String, String>> nearbyPlacesList;
    @Override
    // start a new activity to show the user the list of places we got
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_places_list);
        Intent intent = getIntent();
        nearbyPlacesList = (List<HashMap<String, String>>) intent.getSerializableExtra("places_list");
        PlacesAdapter placesAdapter = new PlacesAdapter(nearbyPlacesList);
        RecyclerView recyclerView = findViewById(R.id.places_recycler);
        recyclerView.setAdapter(placesAdapter);
    }
}