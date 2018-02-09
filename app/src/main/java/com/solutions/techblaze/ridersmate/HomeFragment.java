package com.solutions.techblaze.ridersmate;

/**
 * Created by techblaze on 11/01/18.
 */

import android.Manifest;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends android.support.v4.app.Fragment {
    private ProfilePictureView profilePictureView;
    SharedPreferences prefs;
    String user_id,user_name;
    String users[]={"100000219454975","100001143796013"};

//    ListView new_feed_list;
//    New_feed_adapter adapter;
private List<Home_new_feed> feedList = new ArrayList<>();
    private RecyclerView recyclerView;
    private New_feed_adapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

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

        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId(user_id);
        swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);

        mAdapter = new New_feed_adapter(feedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) ;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedList.clear();
                prepareMovieData();
            }
        });



//        new_feed_list=(ListView)v.findViewById(R.id.new_feed_list);
//        adapter=new New_feed_adapter(getActivity(),users);
//        new_feed_list.setAdapter(adapter);



        return v;

    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }
    private void prepareMovieData() {
        Home_new_feed f_list = new Home_new_feed("100000219454975");
        feedList.add(f_list);

        f_list = new Home_new_feed("100001143796013");
        feedList.add(f_list);



        mAdapter.notifyDataSetChanged();
        onItemsLoadComplete();
    }


}
