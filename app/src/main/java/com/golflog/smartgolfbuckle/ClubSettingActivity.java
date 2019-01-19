package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.golflog.smartgolfbuckle.adapter.GolfClubRecyclerViewAdapter;
import com.golflog.smartgolfbuckle.databinding.ActivityClubSettingBinding;
import com.golflog.smartgolfbuckle.vo.GolfClub;

import java.util.ArrayList;

public class ClubSettingActivity extends AppCompatActivity {

    ActivityClubSettingBinding binding;

    String[] clubKinds = {"D", "3W", "5W", "4I", "5I", "6I", "7I", "8I", "9I", "PW", "AW", "SW", "LW", "PT"};
    ArrayList<GolfClub> mGolfClubList;
    GolfClubRecyclerViewAdapter mGolfClubRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_club_setting);
        LinearLayoutManager manager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvClubList.setLayoutManager(manager);

        printGolfClubList();
    }

    private void initGolfClub() {
        mGolfClubList = new ArrayList<>();
        for (String kind : clubKinds)
            mGolfClubList.add(new GolfClub(kind, "None", "None", "Graphite"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        printGolfClubList();
    }

    private void printGolfClubList() {
        if(SaveSharedPreference.getLoggedUser().getGolfClubList() != null)
            mGolfClubList = SaveSharedPreference.getLoggedUser().getGolfClubList();
        else
            initGolfClub();

        SaveSharedPreference.setGolfClubList(mGolfClubList);
        mGolfClubRecyclerViewAdapter = new GolfClubRecyclerViewAdapter(mGolfClubList, this);
        binding.rvClubList.setAdapter(mGolfClubRecyclerViewAdapter);
    }
}
