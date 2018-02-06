package com.solutions.techblaze.ridersmate;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Trip_defenition extends AppCompatActivity {
    private TextView select_date,start_time,end_date;
    private Button next_button;
    private int mYear, mMonth, mDay, mHour, mMinute;

    int hour;
    int minute;
    private EditText trip_title,trip_info,trip_type,aprox_km,aprox_cost;
    private RadioGroup r_group;
    private RadioButton radioButton;
    RelativeLayout relativeLayout;
    String str_start_date,str_end_date,str_start_time,str_trip_title,str_trip_info,str_trip_type="",str_aprox_km,str_aprox_cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_defenition);
        select_date=(TextView)findViewById(R.id.date_select);
        start_time=(TextView)findViewById(R.id.start_time);
        next_button=(Button)findViewById(R.id.next_button);
        r_group=(RadioGroup)findViewById(R.id.trip_type);
        trip_title=(EditText)findViewById(R.id.trip_name);
        trip_info=(EditText)findViewById(R.id.trip_info);
        end_date=(TextView)findViewById(R.id.date_arrival);
        aprox_km=(EditText)findViewById(R.id.aprox_km);
        aprox_cost=(EditText)findViewById(R.id.aprox_cost);
        relativeLayout=(RelativeLayout)findViewById(R.id.rerouteLayout);
        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                final String month_name = month_date.format(c.getTime());
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Trip_defenition.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                end_date.setText(dayOfMonth + "-" + (month_name) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();




            }
        });

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                SimpleDateFormat month_date = new SimpleDateFormat("MMM");
                final String month_name = month_date.format(c.getTime());
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Trip_defenition.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                select_date.setText(dayOfMonth + "-" + (month_name) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();


            }
        });
        start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
               minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(Trip_defenition.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String state = "am";
                        hour=selectedHour;
                        if(hour > 12){
                            hour -=12;
                            state = "pm";
                        }
                        start_time.setText( hour + " : " + selectedMinute+" "+state);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");

                mTimePicker.show();

            }
        });
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = r_group.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

               // Toast.makeText(getApplicationContext(),radioButton.getText(), Toast.LENGTH_SHORT).show();
                str_trip_title=trip_title.getText().toString();
                str_trip_info=trip_info.getText().toString();

                str_start_date=select_date.getText().toString();
                str_end_date=end_date.getText().toString();
                str_start_time=start_time.getText().toString();
                str_aprox_km=aprox_km.getText().toString();
                str_aprox_cost=aprox_cost.getText().toString();
                Snackbar snackbar = Snackbar
                        .make(relativeLayout,"",Snackbar.LENGTH_LONG);

                if(str_trip_title.equals(""))
                {

                    snackbar.setText("Enter Trip Title");
                    snackbar.show();
                }
                else
                {
                    if(str_trip_info.equals(""))
                    {

                    }
                    else
                    {
                        if (r_group.getCheckedRadioButtonId() != -1)
                        {
                            str_trip_type=radioButton.getText().toString();
                            if (str_start_date.equals("Select Date")) {
                                snackbar.setText("Select Date");
                                snackbar.show();

                            } else {
                                if (str_start_time.equals("Select Time")) {
                                    snackbar.setText("Select Time");
                                    snackbar.show();

                                } else {
                                    if (str_end_date.equals("Select Date")) {
                                        snackbar.setText("Select Date");
                                        snackbar.show();

                                    } else {
                                        if (str_aprox_km.equals("")) {
                                            str_aprox_km="null";
                                            if (str_aprox_cost.equals("")) {
                                                str_aprox_cost="null";
                                                Intent in = new Intent(Trip_defenition.this, Route_select.class);
                                                in.putExtra("title", str_trip_title);
                                                in.putExtra("info", str_trip_info);
                                                in.putExtra("type", str_trip_type);
                                                in.putExtra("start_date", str_start_date);
                                                in.putExtra("end_date", str_end_date);
                                                in.putExtra("start_time", str_start_time);
                                                in.putExtra("aprox_km", str_aprox_km);
                                                in.putExtra("aprox_cost", str_aprox_cost);

                                                startActivity(in);

                                            }
                                        } else {
                                            if (str_aprox_cost.equals("")) {
                                                str_aprox_cost="null";

                                            } else {
                                                Intent in = new Intent(Trip_defenition.this, Route_select.class);
                                                in.putExtra("title", str_trip_title);
                                                in.putExtra("info", str_trip_info);
                                                in.putExtra("type", str_trip_type);
                                                in.putExtra("start_date", str_start_date);
                                                in.putExtra("end_date", str_end_date);
                                                in.putExtra("start_time", str_start_time);
                                                in.putExtra("aprox_km", str_aprox_km);
                                                in.putExtra("aprox_cost", str_aprox_cost);

                                                startActivity(in);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            snackbar.setText("Select Trip Type");
                            snackbar.show();
                        }
                    }
                }

              //  Toast.makeText(getApplicationContext(),str_trip_title+str_trip_info+str_trip_type+str_start_date+str_start_time+str_start_time,Toast.LENGTH_SHORT).show();




            }
        });
    }
}
