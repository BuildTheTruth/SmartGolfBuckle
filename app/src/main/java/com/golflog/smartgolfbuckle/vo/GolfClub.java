package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class GolfClub implements Parcelable {
    private String kind;
    private String tagID;
    private String maker;
    private String shaft;
    private boolean tagChanged;

    public GolfClub(String kind, String tagID, String maker, String shaft) {
        this.kind = kind;
        this.tagID = tagID;
        this.maker = maker;
        this.shaft = shaft;
        tagChanged = false;
    }

    protected GolfClub(Parcel in) {
        kind = in.readString();
        tagID = in.readString();
        maker = in.readString();
        shaft = in.readString();
        tagChanged = in.readByte() != 0;
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

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
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

    public boolean isTagChanged() {
        return tagChanged;
    }

    public void setTagChanged(boolean tagChanged) {
        this.tagChanged = tagChanged;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kind);
        dest.writeString(tagID);
        dest.writeString(maker);
        dest.writeString(shaft);
        dest.writeByte((byte) (tagChanged ? 1 : 0));
    }
}
