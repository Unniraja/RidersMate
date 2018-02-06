package com.solutions.techblaze.ridersmate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.solutions.techblaze.ridersmate.adapters.My_Trips_custom_Adapter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        my_trip_list=(ListView)v.findViewById(R.id.my_trip_list);
        title.add("Kochi to kodaikanal");
        title.add("Kochi to Moonar");
        title.add("kochi to Nelliyambathy");
        host_by.add("Sarath M J");
        host_by.add("Unniraja");
        host_by.add("Jibin M");
        adapter=new My_Trips_custom_Adapter(getActivity(),title,host_by);
        my_trip_list.setAdapter(adapter);


        return v;
    }
}