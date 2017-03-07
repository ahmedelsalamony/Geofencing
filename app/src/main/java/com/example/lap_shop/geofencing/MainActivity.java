package com.example.lap_shop.geofencing;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lap_shop.geofencing.Geofencing.Constants;
import com.example.lap_shop.geofencing.Gps.GooglePlayServicesManager;
import com.example.lap_shop.geofencing.Gps.LocationManager;

import com.example.lap_shop.geofencing.Gps.MyService;
import com.google.android.gms.location.LocationSettingsStates;



public class MainActivity extends AppCompatActivity {
    int flag = 0;
    private static final int INITIAL_REQUEST = 1337;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (GooglePlayServicesManager.checkPlayServices(this)) {
            LocationManager.checkLocationEnableAll(this);

            if (flag == 0) {

                    Intent i = new Intent(getApplicationContext(), MyService.class);
                    startService(i);

            }

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case LOCATION_REQUEST:

                    Intent i = new Intent(getApplicationContext(), MyService.class);
                    startService(i);

                //  new CurrentLocation(MainActivity.this);

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        flag = 1;

                            Intent i = new Intent(getApplicationContext(), MyService.class);
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






}
