package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class ShotData implements Parcelable {
    private GolfClub shotGolfClub;
    private String latitude;
    private String longitude;
    private String altitude;
    private String distance;
    private String altDifference;
    private LatLng position;
    private String adImage;
    private int hole;
    private int par;

    public ShotData(GolfClub shotGolfClub, String latitude, String longitude, String altitude, int hole, int par) {
        this.shotGolfClub = shotGolfClub;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.distance = "0";
        this.altDifference = "0";
        this.hole = hole;
        this.par = par;
        position = new LatLng(Float.parseFloat(latitude), Float.parseFloat(longitude));
    }

    protected ShotData(Parcel in) {
        shotGolfClub = in.readParcelable(GolfClub.class.getClassLoader());
        latitude = in.readString();
        longitude = in.readString();
        altitude = in.readString();
        distance = in.readString();
        altDifference = in.readString();
        position = in.readParcelable(LatLng.class.getClassLoader());
        adImage = in.readString();
        hole = in.readInt();
        par = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(shotGolfClub, flags);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(altitude);
        dest.writeString(distance);
        dest.writeString(altDifference);
        dest.writeParcelable(position, flags);
        dest.writeString(adImage);
        dest.writeInt(hole);
        dest.writeInt(par);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public int getHole() {
        return hole;
    }

    public void setHole(int hole) {
        this.hole = hole;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }
}
