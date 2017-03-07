package com.example.lap_shop.geofencing.Geofencing;

/**
 * Created by LAP_SHOP on 15/02/2017.
 */

import com.google.android.gms.maps.model.LatLng;


import java.util.HashMap;
import java.util.Map;

/**
 * Constants used in this sample.
 */
public final class Constants {
    static HashMap<String, LatLng> Points = new HashMap<String, LatLng>();
    public Constants() {

    }
    public static void Add(){
        Points.put("SFO", new LatLng(37.621313, -12));
        Points.put("GOOGLE", new LatLng(37.422611, -12));
        Points.put("Location", new LatLng(30.795039, 30.995922));
        System.out.println(Points.size()+"tesr1");
    }


    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */



    public static HashMap<String, LatLng> Geofancing_Points = new HashMap<String, LatLng>();
    static {
        Add();
        System.out.println(Points.size()+"tesr");
        for (Map.Entry<String, LatLng> entry : Points.entrySet()) {
            Geofancing_Points.put(entry.getKey(), entry.getValue());

        }
    }
}