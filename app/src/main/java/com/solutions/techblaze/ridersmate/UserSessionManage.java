package com.solutions.techblaze.ridersmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Unnraja on 6/23/2016.
 */
public class UserSessionManage {
    // Shared Preferences reference
    SharedPreferences pref;

    // Editor reference for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREFER_NAME = "Login";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_UID = "uid";


    public static final String KEY_U_NAME = "u_name";
    public static final String KEY_F_NAME = "f_name";
    public static final String KEY_L_NAME = "l_name";
    public static final String KEY_EMAIL = "email";


    public UserSessionManage(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLoginSession(String uid, String u_name,String f_name,String l_name,String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        // Storing name in pref
        //editor.putString(KEY_UID, response);
        editor.putString(KEY_UID,uid);
        editor.putString(KEY_U_NAME,u_name);
        editor.putString(KEY_F_NAME,f_name);
        editor.putString(KEY_L_NAME,l_name);
        editor.putString(KEY_EMAIL,email);
        editor.commit();
    }



    public boolean checkLogin(){
        // Check login status
        if(!this.isUserLoggedIn()){

            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, Rider_Home.class);

            // Closing all the Activities from stack
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);

            return true;
        }
        return false;
    }
    public HashMap<String, String> getUserDetails(){

        //Use hashmap to store user credentials
        HashMap<String,String> user = new HashMap<String,String>();

        // user name
       // user.put(KEY_UID, pref.getString(KEY_UID, null));
        user.put(KEY_UID, pref.getString(KEY_UID, null));
       // user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));
       // user.put(KEY_MOBILE,pref.getString(KEY_MOBILE,null));


        // return user
        return user;
    }
    /**
     * Clear session details
     * */
    public void logoutUser(){

        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent i = new Intent(_context, Login.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }
    public void switch_user()
    {
        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();


    }


    // Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }



}


