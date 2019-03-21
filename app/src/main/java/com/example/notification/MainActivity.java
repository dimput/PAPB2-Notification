package com.example.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID=0;
    private static final String ACTION_UPDATE_NOTIFICATION=
            "com.example.notification.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_DELETE_NOTIFICATION=
            "com.example.notification.ACTION_CANCEL_NOTIFICATION";
    private NotificationReceiver receiver = new NotificationReceiver();
    private String NOTIFICATION_URL = "https://Google.com";

    private Button notifButton;
    private Button updateButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifButton = findViewById(R.id.notify);
        updateButton = findViewById(R.id.update);
        deleteButton = findViewById(R.id.delete);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

//        Notification
        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });

//        Update Button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotification();
            }
        });

        //        Delete Button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNotification();
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UPDATE_NOTIFICATION);
        intentFilter.addAction(ACTION_DELETE_NOTIFICATION);
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onDestroy(){
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void updateNotification(){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        Intent notifcationIntent = new Intent(this,MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent
                .getActivity(this,NOTIFICATION_ID,notifcationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(NOTIFICATION_URL));

        PendingIntent learnMorePendingIntent = PendingIntent
                .getActivity(this,NOTIFICATION_ID,learnMoreIntent
                        ,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Dimas Putra Y")
                        .setContentText("DIMPUT")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .addAction(R.mipmap.ic_launcher,"Learn More",
                                learnMorePendingIntent)
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(image)
                                .setBigContentTitle("Notification Updated !"));
        notificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
    }

    private void deleteNotification(){
        notificationManager.cancel(NOTIFICATION_ID);
    }

    private void sendNotification(){
        Intent notifcationIntent = new Intent(this,MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent
                .getActivity(this,NOTIFICATION_ID,notifcationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(NOTIFICATION_URL));

        PendingIntent learnMorePendingIntent = PendingIntent
                .getActivity(this,NOTIFICATION_ID,learnMoreIntent
                ,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Dimas Putra Y")
                        .setContentText("DIMPUT")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .addAction(R.mipmap.ic_launcher,"Learn More",
                                    learnMorePendingIntent)
                        .setContentIntent(notificationPendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
    }

    private class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_UPDATE_NOTIFICATION:
                    updateNotification();
                    break;
                case ACTION_DELETE_NOTIFICATION:
                    deleteNotification();
            }
        }
    }
}