package com.example.yasmina.udmap.note;

import java.util.List;

/**
 * Created by yasmina on 12/03/18.
 */

public class Feed {

    private String heading;


    private List<Info> infoList;

    public Feed(){}

    public  Feed(String heading, List<Info> infoList){
        this.heading = heading;
        this.infoList = infoList;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }
}