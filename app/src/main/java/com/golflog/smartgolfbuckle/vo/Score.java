package com.golflog.smartgolfbuckle.vo;

public class Score {
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
}
