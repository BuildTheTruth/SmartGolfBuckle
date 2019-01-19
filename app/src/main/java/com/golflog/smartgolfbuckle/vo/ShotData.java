package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class ShotData implements Parcelable {
    private GolfClub shotGolfClub;
    private String latitude;
    private String longitude;
    private String altitude;
    private String distance;
    private String altDifference;
    private String holeNum;
    private String parData;

    protected ShotData(Parcel in) {
        shotGolfClub = in.readParcelable(GolfClub.class.getClassLoader());
        latitude = in.readString();
        longitude = in.readString();
        altitude = in.readString();
        distance = in.readString();
        altDifference = in.readString();
        holeNum = in.readString();
        parData = in.readString();
    }

    public static final Creator<ShotData> CREATOR = new Creator<ShotData>() {
        @Override
        public ShotData createFromParcel(Parcel in) {
            return new ShotData(in);
        }

        @Override
        public ShotData[] newArray(int size) {
            return new ShotData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(shotGolfClub, flags);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(altitude);
        dest.writeString(distance);
        dest.writeString(altDifference);
        dest.writeString(holeNum);
        dest.writeString(parData);
    }

    public ShotData(GolfClub shotGolfClub, String latitude, String longitude, String altitude, String distance, String altDifference, String holeNum, String parData) {
        this.shotGolfClub = shotGolfClub;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.distance = distance;
        this.altDifference = altDifference;
        this.holeNum = holeNum;
        this.parData = parData;
    }

    public GolfClub getShotGolfClub() {
        return shotGolfClub;
    }

    public void setShotGolfClub(GolfClub shotGolfClub) {
        this.shotGolfClub = shotGolfClub;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAltDifference() {
        return altDifference;
    }

    public void setAltDifference(String altDifference) {
        this.altDifference = altDifference;
    }

    public String getHoleNum() {
        return holeNum;
    }

    public void setHoleNum(String holeNum) {
        this.holeNum = holeNum;
    }

    public String getParData() {
        return parData;
    }

    public void setParData(String parData) {
        this.parData = parData;
    }
}
