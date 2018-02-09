package com.solutions.techblaze.ridersmate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class New_status_update extends AppCompatActivity {

    private ProfilePictureView profilePictureView;
    SharedPreferences prefs;
    String user_id,user_name;
    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_status_update);
        profilePictureView = (ProfilePictureView)findViewById(R.id.user_profile);
        user=(TextView)findViewById(R.id.user_name_fb);

        prefs =getSharedPreferences("Login", 0);
        user_id=prefs.getString("uid", null);
        user_name=prefs.getString("f_name", null);

        profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
        profilePictureView.setProfileId(user_id);
        user.setText(user_name);
    }
}
