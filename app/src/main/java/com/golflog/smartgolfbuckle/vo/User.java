package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Jin on 2018-12-27.
 */

public class User implements Parcelable {
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

    protected User(Parcel in) {
        phoneNumber = in.readString();
        password = in.readString();
        name = in.readString();
        gender = in.readString();
        ageGroup = in.readString();
        golfClubList = in.createTypedArrayList(GolfClub.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(ageGroup);
        dest.writeTypedList(golfClubList);
    }
}
