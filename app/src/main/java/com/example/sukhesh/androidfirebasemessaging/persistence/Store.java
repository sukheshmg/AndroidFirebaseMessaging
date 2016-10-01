package com.example.sukhesh.androidfirebasemessaging.persistence;

import android.content.SharedPreferences;

import com.example.sukhesh.androidfirebasemessaging.MapsActivity;

/**
 * Created by sukhesh on 01/10/16.
 */

public enum  Store {
    INSTANCE;

    private SharedPreferences preferences;


    public void setPreferences(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void store(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key) {
        return preferences.getString(key, "");
    }
}
