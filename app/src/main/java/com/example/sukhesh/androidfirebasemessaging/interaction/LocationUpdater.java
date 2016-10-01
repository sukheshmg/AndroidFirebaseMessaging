package com.example.sukhesh.androidfirebasemessaging.interaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.http.HttpResponseCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sukhesh.androidfirebasemessaging.location.Location;
import com.example.sukhesh.androidfirebasemessaging.persistence.Store;

//import org.apache.http.ConnectionReuseStrategy;
//import org.apache.http.HttpException;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpRequest;
//import org.apache.http.HttpRequestInterceptor;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpVersion;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.DefaultConnectionReuseStrategy;
//import org.apache.http.impl.DefaultHttpClientConnection;
//import org.apache.http.message.BasicHttpRequest;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.params.HttpProtocolParams;
//import org.apache.http.params.SyncBasicHttpParams;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.BasicHttpProcessor;
//import org.apache.http.protocol.ExecutionContext;
//import org.apache.http.protocol.HttpContext;
//import org.apache.http.protocol.HttpProcessor;
//import org.apache.http.protocol.HttpRequestExecutor;
//import org.apache.http.protocol.ImmutableHttpProcessor;
//import org.apache.http.protocol.RequestConnControl;
//import org.apache.http.protocol.RequestContent;
//import org.apache.http.protocol.RequestExpectContinue;
//import org.apache.http.protocol.RequestTargetHost;
//import org.apache.http.protocol.RequestUserAgent;
//import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sukhesh on 01/10/16.
 */

public class LocationUpdater {

    private static String url = "http://54.245.13.35:80/v1/locationtracker/location/";
    // private static String url = "http://localhost:9000/v1/locationtracker/location/";

    public static void sendLocation(Location location, Context context) {
        String deviceId = Store.INSTANCE.get("token");
        if(deviceId == null || deviceId.isEmpty()) {
            System.out.println("Haven't yet got the device id");
            return;
        }
        String fullUrl = url + deviceId.trim() + "?langitude=" + location.getLongitude() + "&latitude=" + location.getLatitude();
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
