package com.solutions.techblaze.ridersmate;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Route_select extends AppCompatActivity {

    private TextView start_point,place_start,place_end;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    CardView card_start,card_stop;
    int flag=0,i=0;
    private LatLng start_pos,end_pos;
    MapView m;
    GoogleMap googleMap;
    com.mapbox.mapboxsdk.annotations.Marker marker_one,marker_two;

    private com.mapbox.mapboxsdk.maps.MapView mapView;
    private MapboxMap map;
    private PermissionsManager permissionsManager;
    private LocationLayerPlugin locationPlugin;
    private LocationEngine locationEngine;
    private Location originLocation;
    private com.mapbox.mapboxsdk.annotations.Marker destinationMarker;
    private com.mapbox.mapboxsdk.annotations.Marker origin_marker;
    private com.mapbox.mapboxsdk.geometry.LatLng originCoord;
    private com.mapbox.mapboxsdk.geometry.LatLng destinationCoord;
    private Point originPosition;
    private Point destinationPosition;
    private DirectionsRoute currentRoute;
    private static final String TAG = "DirectionsActivity";
    private NavigationMapRoute navigationMapRoute;
    com.mapbox.mapboxsdk.geometry.LatLng starting_loc,ending_loc;
    double start_lat,start_lng,end_lat,end_lng;
    String user_id;
    SharedPreferences prefs;
    String place_address1,place_address2,str_start_date,str_end_date,str_start_time,str_trip_title,str_trip_info,str_trip_type,str_aprox_km,str_aprox_cost;
    Button save_trip;
    private String url="http://192.168.4.101/Riders/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.access_token));
        setContentView(R.layout.activity_route_select);
        prefs = getSharedPreferences("Login", 0);
        user_id=prefs.getString("uid", null);
        str_trip_title=getIntent().getExtras().getString("title");
        str_trip_info=getIntent().getExtras().getString("info");
        str_trip_type=getIntent().getExtras().getString("type");
        str_start_date=getIntent().getExtras().getString("start_date");
        str_end_date=getIntent().getExtras().getString("end_date");
        str_start_time=getIntent().getExtras().getString("start_time");

        str_aprox_km=getIntent().getExtras().getString("aprox_km");
        str_aprox_cost=getIntent().getExtras().getString("aprox_cost");
        Toast.makeText(getApplicationContext(),str_aprox_km+str_aprox_cost,Toast.LENGTH_SHORT).show();
        start_point=(TextView)findViewById(R.id.start_point);
        place_start=(TextView)findViewById(R.id.place_start);
        place_end=(TextView)findViewById(R.id.place_stop);
        card_start=(CardView)findViewById(R.id.card_start);
        card_stop=(CardView)findViewById(R.id.card_stop);

        save_trip=(Button)findViewById(R.id.save_trip);
        save_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(place_start.getText().equals("Select Start Point"))
                {
                    Toast.makeText(getApplicationContext(),"Select Start Location",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(place_end.getText().equals("Select End Point"))
                    {
                        Toast.makeText(getApplicationContext(),"Select End Location",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {



                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());


                            ConnectivityManager cm =
                                    (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo netInfo = cm.getActiveNetworkInfo();
                            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                                StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                                        new com.android.volley.Response.Listener<String>() {

                                            @Override
                                            public void onResponse(String response) {
                                                String s = "", str = "";

                                                 Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();








                                            }
                                        },
                                        new com.android.volley.Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                                                Log.e("Error",error.toString());
                                            }
                                        }) {
                                    @Override
                                    protected Map<String, String> getParams() {
                                        Map<String, String> params = new HashMap<String, String>();
                                        //  params.put("name",name);
                                      //  params.put("mobile",mobile);
                                        //   params.put("code",code);
                                        params.put("user_id",user_id);
                                        params.put("title", str_trip_title);
                                        params.put("info", str_trip_info);
                                        params.put("type", str_trip_type);
                                        params.put("start_date", str_start_date);
                                        params.put("end_date", str_end_date);
                                        params.put("start_time", str_start_time);
                                        params.put("aprox_km", str_aprox_km);
                                        params.put("aprox_cost", str_aprox_cost);
                                        params.put("start_adr",place_address1);
                                        params.put("end_adr",place_address2);
//                                        params.put("start_lat",String.valueOf(start_lat));
//                                        params.put("start_lng",String.valueOf(start_lng));
//                                        params.put("end_lat",String.valueOf(end_lat));
//                                        params.put("end_lng",String.valueOf(end_lng));





                                        return params;
                                    }
                                };

                                queue.add(strRequest);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"No Internet...Check your Connection!!",Toast.LENGTH_SHORT).show();
                            }

                        }




                }
            }
        });

        card_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flag=1;


                get_place();
            }
        });
        card_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=2;
                get_place();
            }
        });
        mapView = (com.mapbox.mapboxsdk.maps.MapView) findViewById(R.id.mapView);
        mapView.getMapAsync(new com.mapbox.mapboxsdk.maps.OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
                map = mapboxMap;

        //        originCoord = new com.mapbox.mapboxsdk.geometry.LatLng(originLocation.getLatitude(), originLocation.getLongitude());

                mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull com.mapbox.mapboxsdk.geometry.LatLng point) {

//                        if (destinationMarker != null) {
//                            mapboxMap.removeMarker(destinationMarker);
//                        }


                        destinationCoord = point;
                        double lat=destinationCoord.getLatitude();
                        double lng=destinationCoord.getLongitude();
                        com.mapbox.mapboxsdk.geometry.LatLng ltt=new com.mapbox.mapboxsdk.geometry.LatLng(lat,lng);



                    };
                });
            };
        });

    }


    private void getRoute(Point origin, Point destination) {
        NavigationRoute.builder()
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()
                .getRoute(new Callback<DirectionsResponse>() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {
                        // You can get the generic HTTP info about the response
                        Log.d(TAG, "Response code: " + response.code());
                        if (response.body() == null) {
                            Log.e(TAG, "No routes found, make sure you set the right user and access token.");
                            return;
                        } else if (response.body().routes().size() < 1) {
                            Log.e(TAG, "No routes found");
                            return;
                        }

                        currentRoute = response.body().routes().get(0);

                        // Draw the route on the map
                        if (navigationMapRoute != null) {
                            navigationMapRoute.removeRoute();
                        } else {
                            navigationMapRoute = new NavigationMapRoute(null, mapView, map, R.style.NavigationMapRoute);
                        }
                        navigationMapRoute.addRoute(currentRoute);
                    }

                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });
    }




    public void get_place()
    { try {
        Intent intent =
                new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                        .build(Route_select.this);
        startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
    } catch (GooglePlayServicesRepairableException e) {
        // TODO: Handle the error.
    } catch (GooglePlayServicesNotAvailableException e) {
        // TODO: Handle the error.
    }

    }

    public void draw_marker()
    {
        if(flag==1)
        {
            if(marker_one!=null)
            {
                marker_one.remove();
            }
            marker_one= map.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                .position(starting_loc));
        }
        else if(flag==2)
        {
            if(marker_two!=null) {
                marker_two.remove();
            }
            marker_two= map.addMarker(new com.mapbox.mapboxsdk.annotations.MarkerOptions()
                    .position(ending_loc));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
           // Toast.makeText(getApplicationContext(),""+resultCode,Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("Info ", "Place: " + place.getName());

                if(flag==1) {
                    place_address1=place.getAddress().toString();
                    place_start.setText(place.getAddress().toString());
                    start_pos=place.getLatLng();
                    start_lat=start_pos.latitude;
                    start_lng=start_pos.longitude;
                    starting_loc=new com.mapbox.mapboxsdk.geometry.LatLng(start_lat,start_lng);
                  //  Toast.makeText(getApplicationContext(),"Lattitude : "+start_pos.latitude+" Longitude : "+start_pos.longitude,Toast.LENGTH_SHORT).show();
                }
                else if(flag==2)
                {
                    place_address2=place.getAddress().toString();
                    place_end.setText(place.getAddress().toString());
                    end_pos=place.getLatLng();
                    end_lat=end_pos.latitude;
                    end_lng=end_pos.longitude;
                    ending_loc=new com.mapbox.mapboxsdk.geometry.LatLng(end_lat,end_lng);
                  //  Toast.makeText(getApplicationContext(),"Lattitude : "+end_pos.latitude+" Longitude : "+end_pos.longitude,Toast.LENGTH_SHORT).show();
                }
                if(start_pos!=null&&end_pos!=null)
                {
                    originPosition=Point.fromLngLat(starting_loc.getLongitude(), starting_loc.getLatitude());
                    destinationPosition=Point.fromLngLat(ending_loc.getLongitude(), ending_loc.getLatitude());
                    getRoute(originPosition,destinationPosition);



                   // Toast.makeText(getApplicationContext(),"Testing",Toast.LENGTH_SHORT).show();
                }
                Log.e("Address",place.getAddress().toString());
                draw_marker();
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.i("Hello", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
    protected void onStart() {
        super.onStart();

        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
