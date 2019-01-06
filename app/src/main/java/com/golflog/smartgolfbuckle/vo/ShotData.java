package com.golflog.smartgolfbuckle.vo;

public class ShotData {
    private GolfClub shotGolfClub;
    private String latitude;
    private String longitude;
    private String altitude;
    private String distance;
    private String altDifference;

    public ShotData(GolfClub shotGolfClub, String latitude, String longitude, String altitude, String distance, String altDifference) {
        this.shotGolfClub = shotGolfClub;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.distance = distance;
        this.altDifference = altDifference;
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
}
