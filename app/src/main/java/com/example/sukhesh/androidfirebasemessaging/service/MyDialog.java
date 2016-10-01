package com.example.sukhesh.androidfirebasemessaging.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

/**
 * Created by sukhesh on 02/10/16.
 */

public class MyDialog extends Activity {
    private String messageId;
    private String message;

    public MyDialog(String messageId, String message) {
        this.message = message;
        this.messageId = messageId;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Promotion message");
        alertDialog.setMessage(message);
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);

        alertDialog.show();

    }
}
