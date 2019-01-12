package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jin on 2018-12-27.
 */

public class GolfCourse implements Parcelable {
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

    protected GolfCourse(Parcel in) {
        name = in.readString();
        imageURL = in.readString();
        date = in.readString();
    }

    public static final Creator<GolfCourse> CREATOR = new Creator<GolfCourse>() {
        @Override
        public GolfCourse createFromParcel(Parcel in) {
            return new GolfCourse(in);
        }

        @Override
        public GolfCourse[] newArray(int size) {
            return new GolfCourse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageURL);
        dest.writeString(date);
    }
}
