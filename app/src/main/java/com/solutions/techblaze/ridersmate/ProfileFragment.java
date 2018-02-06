package com.solutions.techblaze.ridersmate;

/**
 * Created by techblaze on 11/01/18.
 */
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class ProfileFragment extends android.support.v4.app.Fragment {
    private ProfilePictureView profilePictureView;
    SharedPreferences prefs;
    String user_id,user_name;
    TextView u_name;

    public static ProfileFragment newInstance() {

        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      //  return inflater.inflate(R.layout.fragment_profile, container, false);
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        profilePictureView = (ProfilePictureView)v.findViewById(R.id.user_profile);
        u_name=(TextView)v.findViewById(R.id.rider_name);
        prefs = getActivity().getSharedPreferences("Login", 0);
        user_id=prefs.getString("uid", null);
        u_name.setText(prefs.getString("u_name", null));
        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId(user_id);
        return v;
    }
}