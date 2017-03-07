package com.example.lap_shop.geofencing.Gps;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.lap_shop.geofencing.Geofencing.Constants;

public class MyService extends Service {
    public MyService() {
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
     //   new CurrentLocation(getApplicationContext());
        new CallGeofancing(getApplicationContext());
    }
}
