package com.golflog.smartgolfbuckle;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.golflog.smartgolfbuckle.adapter.GolfCourseRecyclerViewAdapter;
import com.golflog.smartgolfbuckle.databinding.ActivityMainBinding;
import com.golflog.smartgolfbuckle.vo.GolfClub;
import com.golflog.smartgolfbuckle.vo.GolfCourse;
import com.golflog.smartgolfbuckle.vo.ShotData;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    ArrayList<GolfCourse> tmpGolfCourseList = new ArrayList<>();
    GolfCourseRecyclerViewAdapter mGolfCourseRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvCourseList.setLayoutManager(manager);

        binding.fabUpdateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        generateTempData();
        binding.tvRoundNum.setText(Integer.toString(tmpGolfCourseList.size()));
        mGolfCourseRecyclerViewAdapter = new GolfCourseRecyclerViewAdapter(tmpGolfCourseList, this);
        binding.rvCourseList.setAdapter(mGolfCourseRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_myPage:
                break;
            case R.id.item_tagRegister:
                Intent intent = new Intent(getApplicationContext(), ClubSettingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.item_helper:
                break;
            case R.id.item_logout:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateTempData() {
        GolfClub golfClub1 = new GolfClub("D", "1af2dc788", "Master", "Graphite");
        GolfClub golfClub2 = new GolfClub("3W", "2af2da241", "Ace", "Graphite");
        GolfClub golfClub3 = new GolfClub("PW", "5af00188", "Nice", "Steel");
        GolfClub golfClub4 = new GolfClub("PT", "faf2dc788", "Good", "Steel");

        ShotData shotData1 = new ShotData(golfClub1, "37.024832", "127.648228", "279", 1, 5);
        ShotData shotData2 = new ShotData(golfClub2, "37.023390", "127.649647", "258", 1, 5);
        ShotData shotData3 = new ShotData(golfClub3, "37.022140", "127.650592", "271", 1, 5);
        ShotData shotData4 = new ShotData(golfClub4, "37.021564", "127.650489", "268", 1, 5);
        ShotData shotData5 = new ShotData(null, "37.021438", "127.650520", "264", 1, 5);


        ShotData shotData6 = new ShotData(golfClub2, "37.011753", "127.390220", "268", 2, 4);
        ShotData shotData7 = new ShotData(golfClub4, "37.011753", "127.390186", "268", 2, 4);

        ShotData shotData8 = new ShotData(golfClub1, "37.011753", "127.390270", "268", 3, 4);
        ShotData shotData9 = new ShotData(golfClub2, "37.011753", "127.390180", "268", 3, 4);
        ShotData shotData10 = new ShotData(golfClub3, "37.011753", "127.390140", "268", 3, 4);
        ShotData shotData11 = new ShotData(golfClub4, "37.011753", "127.390130", "268", 3, 4);
        ShotData shotData12 = new ShotData(golfClub4, "37.011753", "127.390126", "268", 3, 4);
        ArrayList<ShotData> shotDataList = new ArrayList<>();
        shotDataList.add(shotData1);
        shotDataList.add(shotData2);
        shotDataList.add(shotData3);
        shotDataList.add(shotData4);
        shotDataList.add(shotData5);
        shotDataList.add(shotData6);
        shotDataList.add(shotData7);
        shotDataList.add(shotData8);
        shotDataList.add(shotData9);
        shotDataList.add(shotData10);
        shotDataList.add(shotData11);
        shotDataList.add(shotData12);

        // Distance 계산
        shotData1.setDistance(calculateDistance(shotData1.getPosition(), shotData2.getPosition()));
        shotData2.setDistance(calculateDistance(shotData2.getPosition(), shotData3.getPosition()));
        shotData3.setDistance(calculateDistance(shotData3.getPosition(), shotData4.getPosition()));
        shotData4.setDistance(calculateDistance(shotData4.getPosition(), shotData5.getPosition()));

        // Altitude Difference 계산
        shotData1.setAltDifference(calculateAltDifference(shotData1, shotData2));
        shotData2.setAltDifference(calculateAltDifference(shotData2, shotData3));
        shotData3.setAltDifference(calculateAltDifference(shotData3, shotData4));
        shotData4.setAltDifference(calculateAltDifference(shotData4, shotData5));

        GolfCourse golfCourse1 = new GolfCourse("레인보우 힐스", "img", "2019-01-06", shotDataList);
        GolfCourse golfCourse2 = new GolfCourse("안드레아스", "img", "2019-02-24", shotDataList);
        GolfCourse golfCourse3 = new GolfCourse("레이크우드", "img", "2019-02-24", shotDataList);
        GolfCourse golfCourse4 = new GolfCourse("레이크 힐스", "img", "2019-02-24", shotDataList);
        tmpGolfCourseList.add(golfCourse1);
        tmpGolfCourseList.add(golfCourse2);
        tmpGolfCourseList.add(golfCourse3);
        tmpGolfCourseList.add(golfCourse4);
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
