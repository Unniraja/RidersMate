package com.solutions.techblaze.ridersmate;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class Manage_Polution_Certificate extends AppCompatActivity {

    private Button add_polution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__polution__certificate);
        add_polution=(Button)findViewById(R.id.add_pollution);
        add_polution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Manage_Polution_Certificate.this);
                // dialog.setContentView(R.layout.alert_list_radio);

                View customView = LayoutInflater.from(Manage_Polution_Certificate.this).inflate(
                        R.layout.popup_pollution_list, null, false);

                dialog.setView(customView);

                dialog.show();



            }
        });
    }
}
