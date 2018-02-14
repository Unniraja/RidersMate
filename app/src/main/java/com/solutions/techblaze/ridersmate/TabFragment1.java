package com.solutions.techblaze.ridersmate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.solutions.techblaze.ridersmate.adapters.My_Trips_Adapter;
import com.solutions.techblaze.ridersmate.adapters.My_Trips_custom_Adapter;
import com.solutions.techblaze.ridersmate.adapters.New_feed_adapter;
import com.solutions.techblaze.ridersmate.interfaces.CustomItemClickListener;
import com.solutions.techblaze.ridersmate.models.Home_new_feed;
import com.solutions.techblaze.ridersmate.models.My_Event_Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by techblaze on 23/01/18.
 */

public class TabFragment1 extends Fragment {
    List<String> title=new ArrayList<String>();
    List<String> host_by=new ArrayList<String>();
    ListView my_trip_list;
    My_Trips_custom_Adapter adapter;
    private List<My_Event_Model> feedList = new ArrayList<>();
    private RecyclerView recyclerView;
    private My_Trips_Adapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);

       // my_trip_list=(ListView)v.findViewById(R.id.my_trip_list);


        swipeRefreshLayout=(SwipeRefreshLayout)v.findViewById(R.id.swipeRefreshLayout);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) ;
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new My_Trips_Adapter(feedList,new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("Testing", "clicked position:" + position);
                //Toast.makeText(getActivity().getApplicationContext(),"Position :"+position,Toast.LENGTH_SHORT).show();
               // long postId = data.get(position).getID();
                // do what ever you want to do with it
                Intent trip_detail=new Intent(getActivity(),Trip_detail_view.class);
                startActivity(trip_detail);
            }
        });
        recyclerView.setAdapter(mAdapter);

       prepareMovieData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                feedList.clear();
             prepareMovieData();
            }
        });


        return v;
    }
    private void prepareMovieData() {
        My_Event_Model f_list = new My_Event_Model("Kochi to kodaikanal");
        feedList.add(f_list);

        f_list = new My_Event_Model("Kochi to Moonar");
        feedList.add(f_list);

        f_list = new My_Event_Model("kochi to Nelliyambathy");
        feedList.add(f_list);




        mAdapter.notifyDataSetChanged();
        onItemsLoadComplete();
    }
    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        swipeRefreshLayout.setRefreshing(false);
    }
}