package com.golflog.smartgolfbuckle;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.golflog.smartgolfbuckle.adapter.GolfCourseRecyclerViewAdapter;
import com.golflog.smartgolfbuckle.databinding.ActivityMainBinding;
import com.golflog.smartgolfbuckle.example.ExampleGolfCourseList;
import com.golflog.smartgolfbuckle.vo.GolfCourse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    ExampleGolfCourseList example = new ExampleGolfCourseList();
    ArrayList<GolfCourse> exGolfCourseList = new ArrayList<>();
    GolfCourseRecyclerViewAdapter mGolfCourseRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        exGolfCourseList = example.getExGolfCourseList();

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

        binding.tvRoundNum.setText(String.valueOf(exGolfCourseList.size()));
        mGolfCourseRecyclerViewAdapter = new GolfCourseRecyclerViewAdapter(exGolfCourseList, this);
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
}
