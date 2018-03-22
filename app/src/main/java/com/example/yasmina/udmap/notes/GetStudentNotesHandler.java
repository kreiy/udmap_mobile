package com.example.yasmina.udmap.notes;

/**
 * Created by cyrano on 22/03/18.
 */

public interface GetStudentNotesHandler<T> {
    void notesAvailable(T notes);
    void notesNotExist();
}
