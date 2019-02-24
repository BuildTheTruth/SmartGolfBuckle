package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.adapter.ShotDataRecyclerViewAdapter;
import com.golflog.smartgolfbuckle.databinding.FragmentRecordAnalysisBinding;
import com.golflog.smartgolfbuckle.vo.GolfCourse;
import com.golflog.smartgolfbuckle.vo.ShotData;

import java.util.ArrayList;

public class RecordAnalysisFragment extends Fragment {
    FragmentRecordAnalysisBinding binding;
    ShotDataRecyclerViewAdapter mShotDataRecyclerViewAdapter;
    static GolfCourse mGolfCourse;
    private ArrayList<ShotData> mShotDataList;
    private ArrayList<ShotData> mFlagDataList = new ArrayList<>();

    public RecordAnalysisFragment() {

    }

    public static RecordAnalysisFragment newInstance(GolfCourse selectedGolfCourse) {
        mGolfCourse = new GolfCourse(selectedGolfCourse);
        return new RecordAnalysisFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_record_analysis, container, false);
        mShotDataList = mGolfCourse.getShotDataList();
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvShotList.setLayoutManager(manager);

        removeFlagData();
        mShotDataRecyclerViewAdapter = new ShotDataRecyclerViewAdapter(mShotDataList, getContext());
        binding.rvShotList.setAdapter(mShotDataRecyclerViewAdapter);

        return binding.getRoot();
    }

    private void removeFlagData() {
        for (ShotData shot : mShotDataList)
            if (shot.getShotGolfClub() == null)
                mFlagDataList.add(shot);

        for (ShotData flag : mFlagDataList)
            mShotDataList.remove(flag);
    }
}
