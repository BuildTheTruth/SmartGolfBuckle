package com.golflog.smartgolfbuckle.example;

import android.location.Location;

import com.golflog.smartgolfbuckle.vo.GolfClub;
import com.golflog.smartgolfbuckle.vo.GolfCourse;
import com.golflog.smartgolfbuckle.vo.ShotData;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Locale;

public class ExampleGolfCourseList {
    private ArrayList<GolfCourse> exGolfCourseList = new ArrayList<>();

    public ExampleGolfCourseList() {
        ArrayList<GolfClub> golfClubs = new ArrayList<>();
        ArrayList<ShotData> shotDatas = new ArrayList<>();
        ArrayList<GolfCourse> golfCourses = new ArrayList<>();

        golfClubs.add(new GolfClub("D", "1af2dc788", "Master", "Graphite"));
        golfClubs.add(new GolfClub("3W", "2af2da241", "Ace", "Graphite"));
        golfClubs.add(new GolfClub("PW", "5af00188", "Nice", "Steel"));
        golfClubs.add(new GolfClub("PT", "faf2dc788", "Good", "Steel"));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 1, 5));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 1, 5));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 1, 5));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 1, 5));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 1, 5));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 2, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 2, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 2, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 2, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 2, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 3, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 3, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 3, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 3, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650500", "268", 3, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 3, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 4, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 4, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 4, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 4, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 4, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 5, 3));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 5, 3));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 5, 3));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 5, 3));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 5, 3));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 6, 5));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 6, 5));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 6, 5));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 6, 5));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 6, 5));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 6, 5));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 7, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 7, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 7, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.022140", "127.650592", "271", 7, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 7, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 7, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 8, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 8, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 8, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.022140", "127.650592", "271", 8, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 8, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 8, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 9, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 9, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 9, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 9, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 9, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 10, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 10, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 10, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.022140", "127.650592", "271", 10, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 10, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 10, 4));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 11, 5));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 11, 5));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 11, 5));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.022140", "127.650592", "271", 11, 5));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 11, 5));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 11, 5));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 12, 3));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 12, 3));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 12, 3));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 12, 3));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 13, 3));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 13, 3));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 13, 3));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 13, 3));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 13, 3));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 14, 5));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 14, 5));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 14, 5));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.022140", "127.650592", "271", 14, 5));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 14, 5));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 15, 3));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.023390", "127.649647", "258", 15, 3));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.021564", "127.650489", "268", 15, 3));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 15, 3));

        shotDatas.add(new ShotData(golfClubs.get(0), "37.024832", "127.648228", "279", 16, 4));
        shotDatas.add(new ShotData(golfClubs.get(1), "37.024012", "127.649120", "264", 16, 4));
        shotDatas.add(new ShotData(golfClubs.get(2), "37.023390", "127.649647", "258", 16, 4));
        shotDatas.add(new ShotData(golfClubs.get(3), "37.022140", "127.650592", "271", 16, 4));
        shotDatas.add(new ShotData(null, "37.021438", "127.650520", "264", 16, 4));

        // Distance 계산
        for (int i = 0; i < shotDatas.size() - 1; i++) {
            if (shotDatas.get(i).getShotGolfClub() == null)
                continue;
            shotDatas.get(i).setDistance(calculateDistance(shotDatas.get(i).getPosition(), shotDatas.get(i + 1).getPosition()));
            shotDatas.get(i).setAltDifference(calculateAltDifference(shotDatas.get(i), shotDatas.get(i + 1)));

        }

        golfCourses.add(new GolfCourse("레인보우 힐스", "img", "2019-01-06", shotDatas));
        golfCourses.add(new GolfCourse("안드레아스", "img", "2018-12-24", shotDatas));
        golfCourses.add(new GolfCourse("레이크우드", "img", "2018-11-12", shotDatas));
        golfCourses.add(new GolfCourse("레이크 힐스", "img", "2018-08-07", shotDatas));

        exGolfCourseList.addAll(golfCourses);
    }

    public ArrayList<GolfCourse> getExGolfCourseList() {
        return exGolfCourseList;
    }

    private String calculateDistance(LatLng pos1, LatLng pos2) {
        Location locationA = new Location("A");
        locationA.setLatitude(pos1.latitude);
        locationA.setLongitude(pos1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(pos2.latitude);
        locationB.setLongitude(pos2.longitude);
        return String.format(Locale.KOREAN, "%.2f", locationA.distanceTo(locationB));
    }

    private String calculateAltDifference(ShotData shot1, ShotData shot2) {
        int altitude1 = Integer.parseInt(shot1.getAltitude());
        int altitude2 = Integer.parseInt(shot2.getAltitude());
        return String.valueOf(altitude2 - altitude1);
    }
}
