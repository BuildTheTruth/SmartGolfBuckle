package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class GolfClub implements Parcelable {
    private String kind;
    private String tag;
    private String maker;
    private String shaft;
    private boolean tagChanged;

    protected GolfClub(Parcel in) {
        kind = in.readString();
        tag = in.readString();
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
        dest.writeByte((byte) (tagChanged ? 1 : 0));
    }

    public GolfClub(String kind, String tag, String maker, String shaft) {
        this.kind = kind;
        this.tag = tag;
        this.maker = maker;
        this.shaft = shaft;
        this.tagChanged = false;
    }

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

    public boolean getTagChanged() {
        return tagChanged;
    }

    public void setTagChanged(boolean tagChanged) {
        this.tagChanged = tagChanged;
    }
}
