package com.example.lap_shop.geofencing.Service_Geofencing;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.lap_shop.geofencing.Tools.CurrentLocation;


public class StartGeofencing extends Service {

    public StartGeofencing() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("On Start ");
        super.onStartCommand(intent, flags, startId);

        return START_STICKY;
    }

    @Override
    public void onCreate() {


        System.out.println("On Craete");


        new CurrentLocation(getApplicationContext());
//        new CallGeofancing(getApplicationContext());
    }
}
