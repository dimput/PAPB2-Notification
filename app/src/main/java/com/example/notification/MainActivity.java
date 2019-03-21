package com.example.notification;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID=0;
    private Button notifButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notifButton = findViewById(R.id.notify);
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
    }
    private void sendNotification(){
        NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Dimas Putra Y")
                        .setContentText("DIMPUT")
                        .setSmallIcon(R.mipmap.ic_launcher);
        notificationManager.notify(NOTIFICATION_ID,notifyBuilder.build());
    }
}