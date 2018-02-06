package com.solutions.techblaze.ridersmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class Login extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    private ProfilePictureView profilePictureView;
    SharedPreferences prefs;

    UserSessionManage sessionManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        sessionManage=new UserSessionManage(getApplicationContext());
        prefs = this.getSharedPreferences("Login",0);
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        if(sessionManage.isUserLoggedIn())
        {
            Intent in=new Intent(this,Rider_Home.class);
            startActivity(in);
            finish();
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        profilePictureView = (ProfilePictureView)findViewById(R.id.image);
        // If using in a fragment
       // loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getApplicationContext(),"Logged",Toast.LENGTH_SHORT).show();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.e("Main", response.toString());
                                profilePictureView.setPresetSize(ProfilePictureView.NORMAL);
                                try {
                                    sessionManage.createUserLoginSession(object.getString("id"),object.getString("name"));
                                    profilePictureView.setProfileId(object.getString("id"));
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                               // setProfileToView(object);

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e("Error",exception.toString());
            }
        });
      //  boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
       // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
