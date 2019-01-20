package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.golflog.smartgolfbuckle.databinding.ActivityGolfCourseBinding;
import com.golflog.smartgolfbuckle.vo.GolfCourse;

public class GolfCourseActivity extends AppCompatActivity {
    private final int SCORE_ITEM = 0;
    private final int DETAIL_RECORD_ITEM = 1;
    private final int RECORD_ANALYSIS_ITEM = 2;

    ActivityGolfCourseBinding binding;
    GolfCourse mGolfCourse;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_golf_course);
        mGolfCourse = getIntent().getParcelableExtra("SELECTED_COURSE");
        
        binding.toolbar.setTitle(mGolfCourse.getName());
        setSupportActionBar(binding.toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        binding.container.setAdapter(mSectionsPagerAdapter);
        binding.container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        binding.tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(binding.container));

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case SCORE_ITEM:
                    return ScoreFragment.newInstance(mGolfCourse);
                case DETAIL_RECORD_ITEM:
                    return DetailRecordFragment.newInstance();
                case RECORD_ANALYSIS_ITEM:
                    return RecordAnalysisFragment.newInstance(mGolfCourse);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
