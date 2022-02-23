package com.example.nearbyplaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// the first activity the user will see in the App
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // after pressing the button the user will go to maps activity to specify his/her location and
    // the nearby famous landmarks to them
    public void step (View view) {
        Intent intent= new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

}