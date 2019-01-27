package com.golflog.smartgolfbuckle.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class Score  implements Parcelable {
    private String par;
    private String point;
    private String putt;
    private String gir;
    private String fwhit;

    public Score(String par, String point, String putt, String gir, String fwhit) {
        this.par = par;
        this.point = point;
        this.putt = putt;
        this.gir = gir;
        this.fwhit = fwhit;
    }


    protected Score(Parcel in) {
        par = in.readString();
        point = in.readString();
        putt = in.readString();
        gir = in.readString();
        fwhit = in.readString();
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPutt() {
        return putt;
    }

    public void setPutt(String putt) {
        this.putt = putt;
    }

    public String getGir() {
        return gir;
    }

    public void setGir(String gir) {
        this.gir = gir;
    }

    public String getFwhit() {
        return fwhit;
    }

    public void setFwhit(String fwhit) {
        this.fwhit = fwhit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(par);
        dest.writeString(point);
        dest.writeString(putt);
        dest.writeString(gir);
        dest.writeString(fwhit);
    }
}
