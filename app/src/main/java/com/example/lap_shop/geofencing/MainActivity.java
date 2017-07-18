package com.example.lap_shop.geofencing;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lap_shop.geofencing.Tools.GooglePlayServicesManager;
import com.example.lap_shop.geofencing.Tools.LocationManager;

import com.example.lap_shop.geofencing.Service_Geofencing.StartGeofencing;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    int flag = 0;
    private static final int INITIAL_REQUEST = 1337;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;
    static HashMap<String, LatLng> Points = new HashMap<String, LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Add();
        Intent m = new Intent(getApplicationContext(), StartGeofencing.class);
        stopService(m);
        if (GooglePlayServicesManager.checkPlayServices(this)) {
            LocationManager.checkLocationEnableAll(this);

            if (flag == 0) {
                Intent i = new Intent(getApplicationContext(), StartGeofencing.class);
                i.putExtra("hashMap", Points);
                startService(i);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case LOCATION_REQUEST:

                Intent i = new Intent(getApplicationContext(), StartGeofencing.class);
                i.putExtra("hashMap", Points);
                startService(i);

                //  new CurrentLocation(MainActivity.this);

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        flag = 1;

                        Intent i = new Intent(getApplicationContext(), StartGeofencing.class);
                        i.putExtra("hashMap", Points);
                        startService(i);

                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        flag = 1;
                        break;
                    default:
                        break;
                }
                break;


        }


    }


    public static void Add() {
        Points.put("SFO", new LatLng(37.621313, -12));
        Points.put("GOOGLE", new LatLng(37.422611, -12));
        Points.put("Location", new LatLng(30.795000, 30.995922));
        // System.out.println(Points.size()+"tesr1");
    }


}
