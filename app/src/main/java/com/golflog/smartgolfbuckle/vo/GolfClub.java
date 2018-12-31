package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class GolfClub implements Parcelable {
    private String kind;
    private String tag;
    private String maker;
    private String shaft;

    public GolfClub(String kind, String tag, String maker, String shaft) {
        this.kind = kind;
        this.tag = tag;
        this.maker = maker;
        this.shaft = shaft;
    }

    protected GolfClub(Parcel in) {
        kind = in.readString();
        tag = in.readString();
        maker = in.readString();
        shaft = in.readString();
    }

    public static final Creator<GolfClub> CREATOR = new Creator<GolfClub>() {
        @Override
        public GolfClub createFromParcel(Parcel in) {
            return new GolfClub(in);
        }

        @Override
        public GolfClub[] newArray(int size) {
            return new GolfClub[size];
        }
    };

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getShaft() {
        return shaft;
    }

    public void setShaft(String shaft) {
        this.shaft = shaft;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeString(tag);
        dest.writeString(maker);
        dest.writeString(shaft);
    }
}
