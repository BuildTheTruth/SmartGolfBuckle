package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.golflog.smartgolfbuckle.adapter.GolfCourseAdapter;
import com.golflog.smartgolfbuckle.databinding.ActivityMainBinding;
import com.golflog.smartgolfbuckle.vo.GolfClub;
import com.golflog.smartgolfbuckle.vo.GolfCourse;
import com.golflog.smartgolfbuckle.vo.ShotData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    ArrayList<GolfCourse> tmpGolfCourseList = new ArrayList<>();
    GolfCourseAdapter mGolfCourseAdapter;

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
        mGolfCourseAdapter = new GolfCourseAdapter(tmpGolfCourseList, this);
        binding.rvCourseList.setAdapter(mGolfCourseAdapter);

    }

    private void generateTempData() {
        GolfClub golfClub1 = new GolfClub("D", "1af2dc788", "Master", "Graphite");
        GolfClub golfClub2 = new GolfClub("3W", "2af2da241", "Ace", "Graphite");
        GolfClub golfClub3 = new GolfClub("PW", "5af00188", "Nice", "Steel");
        GolfClub golfClub4 = new GolfClub("PT", "faf2dc788", "Good", "Steel");

        ShotData shotData1 = new ShotData(golfClub1, "37.012984", "127.385369", "281", "221", "22");
        ShotData shotData2 = new ShotData(golfClub2, "37.012395", "127.385879", "259", "172", "14");
        ShotData shotData3 = new ShotData(golfClub3, "37.011948", "127.390224", "273", "58", "-5");
        ShotData shotData4 = new ShotData(golfClub4, "37.011753", "127.390186", "268", "10", "1");
        ArrayList<ShotData> shotDataList = new ArrayList<>();
        shotDataList.add(shotData1);
        shotDataList.add(shotData2);
        shotDataList.add(shotData3);
        shotDataList.add(shotData4);

        GolfCourse golfCourse1 = new GolfCourse("레인보우 힐스", "img", "2019-01-06", shotDataList);
        GolfCourse golfCourse2 = new GolfCourse("안드레아스", "img", "2019-02-24", shotDataList);
        GolfCourse golfCourse3 = new GolfCourse("레이크우드", "img", "2019-02-24", shotDataList);
        GolfCourse golfCourse4 = new GolfCourse("레이크 힐스", "img", "2019-02-24", shotDataList);
        tmpGolfCourseList.add(golfCourse1);
        tmpGolfCourseList.add(golfCourse2);
        tmpGolfCourseList.add(golfCourse3);
        tmpGolfCourseList.add(golfCourse4);
    }

}
