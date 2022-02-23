package com.example.nearbyplaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {
    private HashMap<String, String> getSingleNearbyPlace(JSONObject googlePlaceJSON){
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String nameOfPlace = "~NA~";
        String vicinity = "~NA~";
        String latitude= "~NA~";
        String longitude = "~NA~";
        String photoReference = "~NA~";
        String photoWidth="~NA~";
        String photoHeight="~NA~";
        String rating="~NA~";
        try {
            if(!googlePlaceJSON.isNull("name"))
                nameOfPlace = googlePlaceJSON.getString("name");

            if(!googlePlaceJSON.isNull("vicinity"))
                vicinity = googlePlaceJSON.getString("vicinity");

            latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");

            rating=googlePlaceJSON.getString("rating");

            if(!googlePlaceJSON.isNull("photos")){
                photoReference = googlePlaceJSON.getJSONArray("photos").getJSONObject(0).getString("photo_reference");
                photoWidth = googlePlaceJSON.getJSONArray("photos").getJSONObject(0).getString("width");
                photoHeight = googlePlaceJSON.getJSONArray("photos").getJSONObject(0).getString("height");
                googlePlaceMap.put("photo_reference", photoReference);
                googlePlaceMap.put("photo_width", photoWidth);
                googlePlaceMap.put("photo_height", photoHeight);
            }

            googlePlaceMap.put("place_name",nameOfPlace);
            googlePlaceMap.put("vicinity",vicinity);
            googlePlaceMap.put("latitude",latitude);
            googlePlaceMap.put("longitude",longitude);
            googlePlaceMap.put("rating", rating);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;
    }

    private List<HashMap<String, String>> getAllNearbyPlaces(JSONArray jsonArray){
        int counter = jsonArray.length();
        List<HashMap<String, String>> nearbyPlacesList = new ArrayList<>();
        HashMap<String, String> nearbyPlaceMap = null;

        for(int i =0; i<counter; i++){
            try {
                nearbyPlaceMap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
                nearbyPlacesList.add(nearbyPlaceMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return nearbyPlacesList;
    }

    public List<HashMap<String, String>> parse(String jsonData){
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //List<HashMap<String, String>> list= getAllNearbyPlaces(jsonArray);
        return getAllNearbyPlaces(jsonArray);
    }
}
