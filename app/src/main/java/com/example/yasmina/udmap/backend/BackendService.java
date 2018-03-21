package com.example.yasmina.udmap.backend;

import android.support.annotation.NonNull;

import com.example.yasmina.udmap.login.SingInHandler;
import com.example.yasmina.udmap.model.TimeLineModel;
import com.example.yasmina.udmap.signup.CheckStudentHandler;
import com.example.yasmina.udmap.signup.RegistrationHandler;
import com.example.yasmina.udmap.signup.Student;
import com.example.yasmina.udmap.signup.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    private static final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("/2018");
    private static final DatabaseReference generalNewsDatabaseRef;
    private static final DatabaseReference studentsRef;
    private static final   FirebaseAuth auth = FirebaseAuth.getInstance();

    static {
        generalNewsDatabaseRef = database.child("/news/general");
        studentsRef = database.child("/students");
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

    public void registerUser(final UserInfo user, final RegistrationHandler handler){
       checkStudentIsListed(user, new CheckStudentHandler() {
           @Override
           public void studentExists() {
               registerUser(user.getEmail(),user.getPassword(), handler);
           }

           @Override
           public void studentDoesNotExists() {
                handler.registrationFail("You don't have permission to access this app");
           }
       });
    }

    public void singInUser(String email, String password, final SingInHandler handler){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(task.getResult().getUser().isEmailVerified()){
                        handler.success();
                    }else{
                        handler.emailNotVerified();
                    }
                }else{
                    handler.failed();
                }
            }
        });
    }

    //UTILS
    private void checkStudentIsListed(final UserInfo user, final CheckStudentHandler handler){
        final String[] tokens = user.getEmail().split("@");
        studentsRef.child(tokens[0]).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() != null){
                  handler.studentExists();
                }else{
                    handler.studentDoesNotExists();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void registerUser(final String email, final String password,  final RegistrationHandler handler){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                            handler.registrationSuccess();
                        }else{
                            handler.registrationFail("Authentication failed !");
                        }
                    }
                });
    }
}
