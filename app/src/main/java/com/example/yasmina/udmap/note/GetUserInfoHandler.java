package com.example.yasmina.udmap.note;

import com.example.yasmina.udmap.signup.Student;

/**
 * Created by cyrano on 22/03/18.
 */

public interface GetUserInfoHandler {
    void studentExists(Student student);
    void studentDoesNotExists();
}
