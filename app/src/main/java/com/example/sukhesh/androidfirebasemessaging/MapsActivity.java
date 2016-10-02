package com.example.sukhesh.androidfirebasemessaging;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;

import com.example.sukhesh.androidfirebasemessaging.interaction.LocationUpdater;
import com.example.sukhesh.androidfirebasemessaging.interaction.MessageResponseUpdater;
import com.example.sukhesh.androidfirebasemessaging.location.Location;
import com.example.sukhesh.androidfirebasemessaging.persistence.Store;
import com.example.sukhesh.androidfirebasemessaging.service.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMapLongClickListener, OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;

    private Context _context = this;

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            // TODO Auto-generated method stub
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            final String messageId = intent.getStringExtra("messageId");
            final String event = intent.getStringExtra("event");

            AlertDialog dialog = new AlertDialog.Builder(_context)
                    .setTitle("Messate from " + event)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MessageResponseUpdater.sendResponse("yes", messageId, event, _context);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MessageResponseUpdater.sendResponse("no", messageId, event,  _context);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert).create();
            dialog.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Store.INSTANCE.setPreferences(getSharedPreferences("MyPrefs", Context.MODE_PRIVATE));

        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("NEW_MESSAGE"));


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        // Register to receive messages.
        // We are registering an observer (mMessageReceiver) to receive Intents
        // with actions named "custom-event-name".
//        LocalBroadcastManager.getInstance(this).registerReceiver(
//                mMessageReceiver, new IntentFilter("NEW_MESSAGE"));
        super.onResume();
    }

    @Override
    protected void onPause() {
        // Unregister since the activity is paused.
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(
//                mMessageReceiver);
        super.onPause();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(12.9245092, 77.6812929);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Intuit Bangalore"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14.0f));
        mMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng point) {
        // mTapTextView.setText("tapped, point=" + point);
        final Location location = new Location(point.latitude, point.longitude);
        // LocationUpdater.sendLocation(location, this);

        final Context context = this;


        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Location check-in")
                .setMessage("Check into this location?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        LocationUpdater.sendLocation(location, context);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // System.out.println("user said no");
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert).create();
        dialog.show();
    }

    @Override
    public void onMapLongClick(LatLng point) {
        Location location = new Location(point.latitude, point.longitude);
        LocationUpdater.sendLocation(location, this);
    }
}