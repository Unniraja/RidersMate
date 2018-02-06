package com.solutions.techblaze.ridersmate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Edit_User_details extends AppCompatActivity {

    EditText fname,lname;
    SharedPreferences prefs;
    String first_name,last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__user_details);
        fname=(EditText)findViewById(R.id.f_name);
        lname=(EditText)findViewById(R.id.l_name);
        prefs = getSharedPreferences("Login", 0);
        first_name=prefs.getString("f_name", null);
        last_name=prefs.getString("l_name", null);
        fname.setText(first_name);
        lname.setText(last_name);
    }
}
