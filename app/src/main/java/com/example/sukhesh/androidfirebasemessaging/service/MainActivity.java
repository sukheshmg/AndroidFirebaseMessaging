package com.example.sukhesh.androidfirebasemessaging.service;

import android.content.Context;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sukhesh.androidfirebasemessaging.R;
import com.example.sukhesh.androidfirebasemessaging.interaction.LocationUpdater;
import com.example.sukhesh.androidfirebasemessaging.location.LocationService;
import com.example.sukhesh.androidfirebasemessaging.persistence.Store;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Store.INSTANCE.setPreferences(getSharedPreferences("MyPrefs", Context.MODE_PRIVATE));

        final AppCompatActivity activity = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                LocationUpdater.sendLocation(LocationService.getLocationFirstSource(), activity);
            }
        });

        // LocationUpdater.sendLocation(LocationService.getLocationFirstSource(), this);

    }

}
