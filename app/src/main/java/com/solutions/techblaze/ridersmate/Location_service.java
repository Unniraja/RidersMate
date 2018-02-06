package com.solutions.techblaze.ridersmate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;



/**
 * Created by Unnraja on 10/3/2016.
 */
public class Location_service extends Service
{
    private static final String TAG = "BOOMBOOMTESTGPS";
    private LocationManager mLocationManager = null;
    private static final int LOCATION_INTERVAL = 2000;
    private static final float LOCATION_DISTANCE = 0f;
    String mobile,latitude,longitude;


    private class LocationListener implements android.location.LocationListener
    {
        Location mLastLocation;

        public LocationListener(String provider)
        {
            Log.e(TAG, "LocationListener " + provider);

            mLastLocation = new Location(provider);
        }

        @Override
        public void onLocationChanged(Location location)
        {
            Double lat,lon;
            lat=location.getLatitude();
            lon=location.getLongitude();
            latitude=lat.toString();
            longitude=lon.toString();
            Log.e(TAG, "onLocationChanged: " + location);
            Toast.makeText(getApplicationContext(),"Lattitude= "+lat+" Longitude= "+lon,Toast.LENGTH_LONG).show();
            mLastLocation.set(location);


         //   Toast.makeText(getApplicationContext(),mobile,Toast.LENGTH_LONG).show();
//            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//            ConnectivityManager cm =
//                    (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo = cm.getActiveNetworkInfo();
//            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//                StringRequest strRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//
//                            @Override
//                            public void onResponse(String response) {
//                                String s = "", str = "";
//
//
//
//                                //
////                                                Intent in = new Intent(getApplicationContext(), Deal_page.class);
////                                                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                                                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                                                startActivity(in);
////                                                finish();
//
//
//
//
//
//
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getApplicationContext(), "No Internet...Check your Connection!!", Toast.LENGTH_SHORT).show();
//                            }
//                        }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//
//                        params.put("mobile",mobile);
//                        params.put("lat",latitude);
//                        params.put("lon",longitude);
//                        return params;
//                    }
//                };
//
//                queue.add(strRequest);
//            }
//            else
//            {
//                Toast.makeText(getApplicationContext(),"No Internet...Check your Connection!!", Toast.LENGTH_SHORT).show();
//            }


        }

        @Override
        public void onProviderDisabled(String provider)
        {
            Log.e(TAG, "onProviderDisabled: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider)
        {
            Log.e(TAG, "onProviderEnabled: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras)
        {
            Log.e(TAG, "onStatusChanged: " + provider);
        }
    }

    LocationListener[] mLocationListeners = new LocationListener[] {
            new LocationListener(LocationManager.GPS_PROVIDER),
            new LocationListener(LocationManager.NETWORK_PROVIDER)
    };

    @Override
    public IBinder onBind(Intent arg0)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate()
    {
        Log.e(TAG, "onCreate");
        initializeLocationManager();
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[1]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
        try {
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, LOCATION_INTERVAL, LOCATION_DISTANCE,
                    mLocationListeners[0]);
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "gps provider does not exist " + ex.getMessage());
        }
    }

    @Override
    public void onDestroy()
    {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (mLocationManager != null) {
            for (int i = 0; i < mLocationListeners.length; i++) {
                try {
                    mLocationManager.removeUpdates(mLocationListeners[i]);
                } catch (Exception ex) {
                    Log.i(TAG, "fail to remove location listners, ignore", ex);
                }
            }
        }
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager");
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }



}
