package com.example.sukhesh.androidfirebasemessaging.interaction;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sukhesh.androidfirebasemessaging.location.Location;
import com.example.sukhesh.androidfirebasemessaging.persistence.Store;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sukhesh on 02/10/16.
 */

public class MessageResponseUpdater {

    private static String url = "http://54.245.13.35:80/v1/response/message/";

    public static void sendResponse(String response, String messageId, String event,  Context context) {
        String deviceId = Store.INSTANCE.get("token");
        if(deviceId == null || deviceId.isEmpty()) {
            System.out.println("Haven't yet got the device id");
            return;
        }
        String fullUrl = url + messageId + "?deviceId=" + deviceId + "&response=" + response + "&event=" + event;
        System.out.println("url: " + fullUrl);
        sendRequest(fullUrl, context);

    }

    private static void sendRequest(String fullUrl, Context context){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, fullUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // showJSON(response);
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        System.out.println(error.getMessage());
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
