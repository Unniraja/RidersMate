package com.solutions.techblaze.ridersmate;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

public class Manage_Motorcycle extends AppCompatActivity {

    private Button add_motor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__motorcycle);
        add_motor=(Button)findViewById(R.id.add_motor);
        add_motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Manage_Motorcycle.this);
                // dialog.setContentView(R.layout.alert_list_radio);

                View customView = LayoutInflater.from(Manage_Motorcycle.this).inflate(
                        R.layout.popup_motor_list, null, false);

                dialog.setView(customView);

                dialog.show();

            }
        });
    }
}
