package com.solutions.techblaze.ridersmate;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Trip_detail_view extends AppCompatActivity {
    private Button im_going;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail_view);
        im_going=(Button)findViewById(R.id.im_going);
        im_going.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                im_going.setBackgroundColor(getResources().getColor(R.color.bt_back));
            }
        });
    }
}
