package com.example.yasmina.udmap;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yasmina.udmap.backend.BackendService;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "CHANEL_ID";

    private static final DatabaseReference generalNewsRef = FirebaseDatabase.getInstance().getReference().child("/2018/news/general");

    private BackendService backendService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backendService = BackendService.getInstance();
        setContentView(R.layout.activity_main);

        generalNewsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                sendNotification();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                sendNotification();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                sendNotification();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                sendNotification();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                sendNotification();
            }
        });

    }

    private void sendNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle("Test notification")
                .setContentText("new general news available")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }
}
