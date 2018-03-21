package com.example.yasmina.udmap_final;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yasmina on 12/03/18.
 */

public class Feed {

    @SerializedName("category")
    @Expose
    private String heading;

    @SerializedName("arrays")
    @Expose
    private List<Info> infoList;

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
