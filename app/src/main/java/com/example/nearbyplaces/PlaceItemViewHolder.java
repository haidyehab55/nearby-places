package com.example.nearbyplaces;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class PlaceItemViewHolder extends RecyclerView.ViewHolder {
    private TextView placeName;
    private TextView vicinity;
    private TextView rating;
    private RatingBar ratingBar;
    private ImageView placeImage;

    // get the name, vicinity, ratting and the image of the place
    public PlaceItemViewHolder(@NonNull View itemView) {
        super(itemView);
        placeName = itemView.findViewById(R.id.place_name);
        vicinity = itemView.findViewById(R.id.vicinity);
        rating = itemView.findViewById(R.id.rating);
        ratingBar = itemView.findViewById(R.id.rating_bar);
        placeImage = itemView.findViewById(R.id.place_image);
    }

    //set the data of the place to show it to the user
    public void populatePlace(HashMap<String, String> place){
        placeName.setText(place.get("place_name"));
        vicinity.setText(place.get("vicinity"));
        rating.setText(place.get("rating"));
        ratingBar.setRating(Float.parseFloat(place.get("rating")));
        if(place.containsKey("photo_reference")){
            String photoReference= place.get("photo_reference");
            String photoWidth= place.get("photo_width");
            String photoHeight= place.get("photo_height");
            String photoUrl=getPhotoUrl(photoReference, photoWidth, photoHeight);
            Glide.with(itemView).load(photoUrl).override(150,150).into(placeImage);
        }else {
            // make sure Glide doesn't load anything into this view until told otherwise
            Glide.with(itemView).clear(placeImage);
            // remove the placeholder (optional); read comments below
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300,300);
            placeImage.setImageResource(R.drawable.travels);
            placeImage.setLayoutParams(params);
        }
    }

    // get the details of the photo of the place and specify its height and width
    private String getPhotoUrl(String photoReference, String photoWidth, String photoHeight) {
        StringBuilder photoUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?");
        photoUrl.append("photo_reference="+photoReference);
        photoUrl.append("&maxheight=150");
        photoUrl.append("&maxwidth=150");
        photoUrl.append("&key="+itemView.getResources().getString(R.string.places_api_key));
        return photoUrl.toString();
    }
}
