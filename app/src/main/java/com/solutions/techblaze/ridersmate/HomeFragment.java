package com.solutions.techblaze.ridersmate;

/**
 * Created by techblaze on 11/01/18.
 */

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.solutions.techblaze.ridersmate.adapters.New_feed_adapter;
import com.solutions.techblaze.ridersmate.models.Home_new_feed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends android.support.v4.app.Fragment {
    private ProfilePictureView profilePictureView,popup_pro_pic;

    private TextView user;
    SharedPreferences prefs;
    String user_id,user_name;
    String users[]={"100000219454975","100001143796013"};
    JsonArrayRequest req;
    String url="http://192.168.4.104/Riders/select_user_posts.php";

//    ListView new_feed_list;
//    New_feed_adapter adapter;
private List<Home_new_feed> feedList = new ArrayList<>();
    private RecyclerView recyclerView;
    private New_feed_adapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView new_post;

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        profilePictureView = (ProfilePictureView)v.findViewById(R.id.user_profile);

        prefs = getActivity().getSharedPreferences("Login", 0);
        user_id=prefs.getString("uid", null);
        user_name=prefs.getString("f_name", null);
        new_post=(TextView)v.findViewById(R.id.new_post);

        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId(user_id);
        swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);

        mAdapter = new New_feed_adapter(feedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) ;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        get_my_posts();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedList.clear();
                get_my_posts();
            }
        });
        new_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                // dialog.setContentView(R.layout.alert_list_radio);

                View customView = LayoutInflater.from(getActivity()).inflate(
                        R.layout.popup_new_feed, null, false);
                popup_pro_pic = (ProfilePictureView)customView.findViewById(R.id.user_profile);
                user=(TextView)customView.findViewById(R.id.user_name_fb);

                popup_pro_pic.setPresetSize(ProfilePictureView.NORMAL);
                popup_pro_pic.setProfileId(user_id);
                user.setText(user_name);

                dialog.setView(customView);

                dialog.show();


            }
        });



//        new_feed_list=(ListView)v.findViewById(R.id.new_feed_list);
//        adapter=new New_feed_adapter(getActivity(),users);
//        new_feed_list.setAdapter(adapter);

      //  get_my_posts();

        return v;

    }

    public void get_my_posts()
    {

        RequestQueue queue= Volley.newRequestQueue(getActivity().getApplicationContext());
        req=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                // Toast.makeText(getActivity().getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                //  JSONObject json_data = new JSONObject(response);
                for(int i=0;i<response.length();i++)
                {


                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Home_new_feed f_list = new Home_new_feed();
                        f_list.setProPic(obj.getString("fb_id"));
                        f_list.setCaption(obj.getString("post_caption"));
                        f_list.setImg_name(obj.getString("img_name"));
                        f_list.setName(obj.getString("name"));

                        feedList.add(f_list);

                    }
                    catch (JSONException e)
                    {

                    }
                }

                mAdapter.notifyDataSetChanged();
                onItemsLoadComplete();







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }

        });
        queue.add(req);



    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }
    private void prepareMovieData() {
//        Home_new_feed f_list = new Home_new_feed("100000219454975");
//        feedList.add(f_list);
//
//        f_list = new Home_new_feed("100001143796013");
//        feedList.add(f_list);
//
//
//
//        mAdapter.notifyDataSetChanged();
        onItemsLoadComplete();
    }


}
