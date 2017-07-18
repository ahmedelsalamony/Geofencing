//package com.example.lap_shop.geofencing.Gps;
//
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.lap_shop.geofencing.Tools.Constants;
//import com.example.lap_shop.geofencing.Tools.GeofenceErrorMessages;
//import com.example.lap_shop.geofencing.Service_Geofencing.MyIntentServiceGeofenceTransitions;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.ResultCallback;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.Geofence;
//import com.google.android.gms.location.GeofencingRequest;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.model.LatLng;
//
//import java.util.ArrayList;
//import java.util.Map;
//
///**
// * Created by LAP_SHOP on 07/03/2017.
// */
//
//public class CallGeofancing implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status> {
//
//    protected static final String TAG = "MainActivity";
//
//
//    /**
//     * The list of geofences used in this sample.
//     */
//    protected ArrayList<Geofence> mGeofenceList;
//    Context context;
//    /**
//     * Used to keep track of whether geofences were added.
//     */
//    private boolean mGeofencesAdded;
//
//    /**
//     * Used when requesting to add or remove geofences.
//     */
//    private PendingIntent mGeofencePendingIntent;
//    protected GoogleApiClient mGoogleApiClient;
//    private LocationRequest locationRequest;
//
//
//    public CallGeofancing(final Context context) {
//        this.context = context;
//        buildGoogleApiClient();
//        mGoogleApiClient.connect();
////It As OnCreate Of activity
//        mGeofenceList = new ArrayList<Geofence>();
//
//        // Initially set the PendingIntent used in addGeofences() and removeGeofences() to null.
//        mGeofencePendingIntent = null;
//
//        populateGeofenceList();
//
//
//
//
//    }
//
//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        addGeofencesButtonHandler();
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
//
//    @Override
//    public void onResult(@NonNull Status status) {
//
//        if (status.isSuccess()) {
//            // Update state and save in shared preferences.
//            mGeofencesAdded = !mGeofencesAdded;
////            SharedPreferences.Editor editor = mSharedPreferences.edit();
////            editor.putBoolean(Constants.GEOFENCES_ADDED_KEY, mGeofencesAdded);
////            editor.apply();
//
//            // Update the UI. Adding geofences enables the Remove Geofences button, and removing
//            // geofences enables the Add Geofences button.
//            // setButtonsEnabledState();
//          //  sendNotification("Test", "You Here" + "");
//            Toast.makeText(
//                    context,
//                    "hjkhjghjfgdd",
//                    Toast.LENGTH_SHORT
//            ).show();
//        } else {
//            // Get the status code for the error and log it using a user-friendly message.
//            String errorMessage = GeofenceErrorMessages.getErrorString(context,
//                    status.getStatusCode());
//            Log.e(TAG, errorMessage);
//        }
//
//
//
//
//
//    }
//
//
//    public void populateGeofenceList() {
//
//        for (Map.Entry<String, LatLng> entry : Constants.Geofancing_Points.entrySet()) {
//
//            mGeofenceList.add(new Geofence.Builder()
//                    // Set the request ID of the geofence. This is a string to identify this
//                    // geofence.
//                    .setRequestId(entry.getKey())
//
//                    // Set the circular region of this geofence.
//                    .setCircularRegion(
//                            entry.getValue().latitude,
//                            entry.getValue().longitude,
//                            Constants.GEOFENCE_RADIUS_IN_METERS
//                    )
//
//                    // Set the expiration duration of the geofence. This geofence gets automatically
//                    // removed after this period of time.
//                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
//
//                    // Set the transition types of interest. Alerts are only generated for these
//                    // transition. We track entry and exit transitions in this sample.
//                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
//                            Geofence.GEOFENCE_TRANSITION_EXIT)
//
//                    // Create the geofence.
//                    .build());
//        }
//    }
//
//
//    public void addGeofencesButtonHandler() {
//
//
//        try {
//            LocationServices.GeofencingApi.addGeofences(
//                    mGoogleApiClient,
//                    // The GeofenceRequest object.
//
//                    getGeofencingRequest(),
//                    // A pending intent that that is reused when calling removeGeofences(). This
//                    // pending intent is used to generate an intent when a matched geofence
//                    // transition is observed.
//                    getGeofencePendingIntent()
//            ).setResultCallback(this); // Result processed in onResult().
//        } catch (SecurityException securityException) {
//            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
//            // logSecurityException(securityException);
//        }
//    }
//
//    private PendingIntent getGeofencePendingIntent() {
//        // Reuse the PendingIntent if we already have it.
//        if (mGeofencePendingIntent != null) {
//            return mGeofencePendingIntent;
//        }
//        Intent intent = new Intent(context, MyIntentServiceGeofenceTransitions.class);
//        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling
//        // addGeofences() and removeGeofences().
//        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
//
//
//    private GeofencingRequest getGeofencingRequest() {
//        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
//
//        // The INITIAL_TRIGGER_ENTER flag indicates that geofencing service should trigger a
//        // GEOFENCE_TRANSITION_ENTER notification when the geofence is added and if the device
//        // is already inside that geofence.
//        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
//
//        // Add the geofences to be monitored by geofencing service.
//
//        builder.addGeofences(mGeofenceList);
//
//        // Return a GeofencingRequest.
//        return builder.build();
//    }
//
//
//}
