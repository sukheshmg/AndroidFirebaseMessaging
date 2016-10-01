package com.example.sukhesh.androidfirebasemessaging.service;

/**
 * Created by sukhesh on 30/09/16.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.sukhesh.androidfirebasemessaging.persistence.Store;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

//Class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        Store.INSTANCE.store("token", refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project

        Log.e(TAG, "sukhesh token: " + token);
        Store.INSTANCE.store("token", token);
    }

}