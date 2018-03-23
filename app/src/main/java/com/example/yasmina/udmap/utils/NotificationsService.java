package com.example.yasmina.udmap.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.yasmina.udmap.Menu;
import com.example.yasmina.udmap.R;
import com.example.yasmina.udmap.backend.BackendService;
import com.example.yasmina.udmap.news.Category;
import com.example.yasmina.udmap.news.NewsMenuActivity;
import com.example.yasmina.udmap.news.TimeLineActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cyrano on 23/03/18.
 */

public final class NotificationsService  {

    private static final NotificationsService instance = new NotificationsService();

    private static boolean builded = false;

    private static final String CHANNEL_ID = "CHANEL_ID";

    private static final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("/2018");

    public static NotificationsService getInstance(){
        return instance;
    }

    public void constructNotificationHandlers(AppCompatActivity context){
        if(!builded){
         generalNewsNotifier(context);
        }
    }

    private void generalNewsNotifier(final AppCompatActivity context){
        database.child("/news/general").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Intent intent = new Intent(context, TimeLineActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(NewsMenuActivity.CATEGORY, Category.GENERAL);
                intent.putExtra(NewsMenuActivity.EXTRA_WITH_LINE_PADDING, true);

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "CHANEL_ID")
                        .setSmallIcon(R.drawable.app_icon)
                        .setContentTitle("Test notification")
                        .setContentText("new general news available")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                // notificationId is a unique int for each notification that you must define
                notificationManager.notify(001, mBuilder.build());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
