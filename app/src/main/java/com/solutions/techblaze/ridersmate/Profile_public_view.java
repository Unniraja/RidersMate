package com.solutions.techblaze.ridersmate;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.facebook.login.widget.ProfilePictureView;
import com.solutions.techblaze.ridersmate.adapters.HLVAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class Profile_public_view extends AppCompatActivity {
    private ProfilePictureView profilePictureView,popup_pro_pic;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<Integer> alImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_public_view);
        profilePictureView = (ProfilePictureView)findViewById(R.id.user_profile);
        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId("100000219454975");
        RelativeLayout rlc = (RelativeLayout) findViewById(R.id.root_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable)rlc.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        alName = new ArrayList<>(Arrays.asList("Cheesy...", "Crispy... ", "Fizzy...", "Cool..."));
        alImage = new ArrayList<>(Arrays.asList(R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4));

        // Calling the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HLVAdapter(this, alName, alImage);
        mRecyclerView.setAdapter(mAdapter);
    }
}
