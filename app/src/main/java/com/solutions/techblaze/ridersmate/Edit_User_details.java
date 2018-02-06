package com.solutions.techblaze.ridersmate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Edit_User_details extends AppCompatActivity {

    EditText fname,lname,mobile_num,email;
    SharedPreferences prefs;
    String first_name,last_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__user_details);
        fname=(EditText)findViewById(R.id.f_name);
        lname=(EditText)findViewById(R.id.l_name);
        mobile_num=(EditText)findViewById(R.id.mobile_number);
        email=(EditText)findViewById(R.id.email);

        prefs = getSharedPreferences("Login", 0);
        first_name=prefs.getString("f_name", null);
        last_name=prefs.getString("l_name", null);
        email.setText(prefs.getString("email", null));
        fname.setText(first_name);
        lname.setText(last_name);
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        MaterialSpinner citizenship = (MaterialSpinner) findViewById(R.id.spinner);
        citizenship.setItems(countries);
      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
       // citizenship.setAdapter(adapter);
    }
}
