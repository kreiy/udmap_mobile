package com.example.yasmina.udmap.backend;

import com.example.yasmina.udmap.model.TimeLineModel;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyrano on 21/03/18.
 */

public final class BackendService {
    private static final BackendService ourInstance = new BackendService();

    private static final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private static final DatabaseReference generalNewsDatabaseRef;

    static {
        generalNewsDatabaseRef = database.child("/news/general");
    }

    private BackendService() {}

    public static BackendService getInstance() {
        return ourInstance;
    }


    public void getGeneralTimelineNews(final CallbackHandler<List<TimeLineModel>> handler){
        generalNewsDatabaseRef.addValueEventListener(new ValueEventListener() {

           final List<TimeLineModel> generalTimelineNews = new ArrayList<>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                generalTimelineNews.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    generalTimelineNews.add(child.getValue(TimeLineModel.class));
                }
                handler.onData(generalTimelineNews);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                handler.onError("and error occured");
            }
        });
    }

    public void registerUser(final CallbackHandler handler){

    }
}
