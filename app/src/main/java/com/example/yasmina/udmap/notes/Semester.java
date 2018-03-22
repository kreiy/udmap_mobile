package com.example.yasmina.udmap.notes;

import java.util.List;

/**
 * Created by yasmina on 12/03/18.
 */

public class Semester {

    private String heading;


    private List<Note> notes;

    public Semester(){}

    public Semester(String heading, List<Note> notes){
        this.heading = heading;
        this.notes = notes;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}