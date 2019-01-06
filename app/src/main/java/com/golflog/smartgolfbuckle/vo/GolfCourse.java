package com.golflog.smartgolfbuckle.vo;

import java.util.ArrayList;

/**
 * Created by Jin on 2018-12-27.
 */

public class GolfCourse {
    private String name;
    private String imageURL;
    private String date;
    private ArrayList<ShotData> shotDataList;

    public GolfCourse(String name, String imageURL, String date, ArrayList<ShotData> shotDataList) {
        this.name = name;
        this.imageURL = imageURL;
        this.date = date;
        this.shotDataList = shotDataList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<ShotData> getShotDataList() {
        return shotDataList;
    }

    public void setShotDataList(ArrayList<ShotData> shotDataList) {
        this.shotDataList = shotDataList;
    }
}
