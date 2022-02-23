package com.example.nearbyplaces;

import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class NearbyPlaces extends AsyncTask<Object, String, String> {
    private String googlePlaceData, url;
    private GoogleMap mMap;
    MapsActivity mapsActivity;

    public NearbyPlaces(MapsActivity mapsActivity){
        this.mapsActivity = mapsActivity;
    }

    @Override

    // in the background the API will calculate the URL, the specific location and all data of the nearby places
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googlePlaceData = downloadUrl.readTheURL(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlaceData;
    }

    @Override
    // after finishing getting the data of the place the parser will send it to the nearbyPlaceList class
    // to work on it
    protected void onPostExecute(String s) {
        DataParser dataParser = new DataParser();
        List<HashMap<String, String>> nearbyPlacesList = dataParser.parse(s);
        //System.out.println(nearbyPlacesList);
        displayNearbyPlaces(nearbyPlacesList);
        mapsActivity.listPlaces(nearbyPlacesList);
    }

    //here we make sure that all items we got will be displayed
    private void displayNearbyPlaces(List<HashMap<String, String>> nearbyPlacesList){
        for(int i =0 ; i< nearbyPlacesList.size(); i++){
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googleNearbyPlace = nearbyPlacesList.get(i);
            String nameOfPlace = googleNearbyPlace.get("place_name");
            double lat = Double.parseDouble(googleNearbyPlace.get("latitude"));
            double lng = Double.parseDouble(googleNearbyPlace.get("longitude"));
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(nameOfPlace);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        }
    }
}
