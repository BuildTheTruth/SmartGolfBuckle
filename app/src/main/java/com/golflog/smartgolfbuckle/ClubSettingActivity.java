package com.golflog.smartgolfbuckle;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.golflog.smartgolfbuckle.adapter.GolfClubAdapter;
import com.golflog.smartgolfbuckle.databinding.ActivityClubSettingBinding;
import com.golflog.smartgolfbuckle.databinding.ItemClubBinding;
import com.golflog.smartgolfbuckle.vo.GolfClub;

import java.util.ArrayList;

public class ClubSettingActivity extends AppCompatActivity {

    ActivityClubSettingBinding binding;

    String[] clubKinds = {"D", "3W", "5W", "4I", "5I", "6I", "7I", "8I", "9I", "PW", "AW", "SW", "LW", "PT"};
    ArrayList<GolfClub> mGolfClubList;
    GolfClubAdapter mGolfClubAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_club_setting);
        LinearLayoutManager manager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvClubList.setLayoutManager(manager);

        if(SharedPreference.getLoggedUser().getGolfClubList() != null)
            mGolfClubList = SharedPreference.getLoggedUser().getGolfClubList();
        else
            initGolfClub();

        Intent intent = getIntent();
        int changedPosition = intent.getIntExtra("CHANGED_POSITION", -1);

        SharedPreference.setGolfClubList(mGolfClubList);
        mGolfClubAdapter = new GolfClubAdapter(mGolfClubList, this, changedPosition);
        binding.rvClubList.setAdapter(mGolfClubAdapter);

    }

    private void initGolfClub() {
        mGolfClubList = new ArrayList<>();
        for (String kind : clubKinds)
            mGolfClubList.add(new GolfClub(kind, "None", "None", "Graphite"));
    }
}
