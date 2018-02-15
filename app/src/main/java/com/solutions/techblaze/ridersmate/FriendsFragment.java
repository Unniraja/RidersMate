package com.solutions.techblaze.ridersmate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.solutions.techblaze.ridersmate.adapters.My_Friends_custom_Adapter;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    List<String> user_names=new ArrayList<String>();
    List<String> my_places=new ArrayList<String>();
    List<String> fb_id=new ArrayList<String>();
    ListView my_friend_list;
    My_Friends_custom_Adapter adapter;
    public static FriendsFragment newInstance() {

        return new FriendsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        my_friend_list=(ListView)v.findViewById(R.id.friend_list);
        user_names.add("Unniraja");
        user_names.add("Sreekanth");
        my_places.add("vaikom,kerala");
        my_places.add("thalayolaparambu,kerala");
        fb_id.add("100001143796013");
        fb_id.add("100000219454975");
        adapter=new My_Friends_custom_Adapter(getActivity(),user_names,my_places,fb_id);
        my_friend_list.setAdapter(adapter);



        return v;
    }


}
