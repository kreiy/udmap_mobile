package com.example.yasmina.udmap.timetable;

/**
 * Created by cyrano on 22/03/18.
 */

public interface GetTimeTableHandler<T> {
    void timetableExists(T timetable);
    void timetableDoesNotExists();
}
