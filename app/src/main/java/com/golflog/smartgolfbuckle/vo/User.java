package com.golflog.smartgolfbuckle.vo;

import java.util.ArrayList;

/**
 * Created by Jin on 2018-12-27.
 */

public class User {
    private String phoneNumber;
    private String password;
    private String name;
    private String gender;
    private String ageGroup;
    private ArrayList<GolfClub> golfClubList;

    public User(String phoneNumber, String password, String name, String gender, String ageGroup) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.ageGroup = ageGroup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public ArrayList<GolfClub> getGolfClubList() {
        return golfClubList;
    }

    public void setGolfClubList(ArrayList<GolfClub> golfClubList) {
        this.golfClubList = golfClubList;
    }
}
