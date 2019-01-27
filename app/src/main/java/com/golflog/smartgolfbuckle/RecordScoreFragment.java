package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.adapter.ScoreRecyclerViewAdapter;
import com.golflog.smartgolfbuckle.databinding.FragmentRecordScoreBinding;
import com.golflog.smartgolfbuckle.vo.GolfCourse;

public class RecordScoreFragment extends Fragment {
    FragmentRecordScoreBinding binding;
    ScoreRecyclerViewAdapter mScoreRecyclerViewAdapter;
    static GolfCourse mGolfCourse;

    public RecordScoreFragment() {

    }

    public static RecordScoreFragment newInstance(GolfCourse selectedGolfCourse) {
        mGolfCourse = selectedGolfCourse;
        return new RecordScoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_record_score, container, false);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvScoreList.setLayoutManager(manager);
        mScoreRecyclerViewAdapter = new ScoreRecyclerViewAdapter(mGolfCourse.getShotDataList(), getContext());
        binding.rvScoreList.setAdapter(mScoreRecyclerViewAdapter);

        return binding.getRoot();
    }
}
