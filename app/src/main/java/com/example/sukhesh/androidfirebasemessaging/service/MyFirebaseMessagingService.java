package com.example.sukhesh.androidfirebasemessaging.service;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.toolbox.StringRequest;
import com.example.sukhesh.androidfirebasemessaging.MapsActivity;
import com.example.sukhesh.androidfirebasemessaging.R;
import com.example.sukhesh.androidfirebasemessaging.persistence.Store;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by sukhesh on 30/09/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        // Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        String messageId = remoteMessage.getMessageId();
        Map<String,String> map = remoteMessage.getData();
        String type = remoteMessage.getMessageType();
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        // String body = notification.getBody();

        //Calling method to generate notification
        // sendNotification(remoteMessage.getNotification().getBody());
        sendNotification(map.get("message") + " with id " + map.get("id"), map.get("id"), map.get("message"));
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(String messageBody, String messageId, String message) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("Firebase Push Notification")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        notificationManager.notify(0, notificationBuilder.build());

        Intent intent = new Intent("NEW_MESSAGE");
        intent.putExtra("message", message);
        intent.putExtra("messageId", messageId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

//        Looper.prepare();
//
//        new MyDialog(messageId, message);

//        AlertDialog dialog = new AlertDialog.Builder(this)
//                .setTitle("Promotions message")
//                .setMessage(message)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.out.println("user said yes");
//                    }
//                })
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        System.out.println("user said no");
//                    }
//                })
//                .setIcon(android.R.drawable.ic_dialog_alert).create();
//
//        dialog.show();
    }
}
