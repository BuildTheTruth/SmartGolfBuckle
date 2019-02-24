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
import com.golflog.smartgolfbuckle.vo.Score;

import java.util.ArrayList;

public class RecordScoreFragment extends Fragment {
    private final int PAR = 0;
    private final int SCORE = 1;
    private final int PUTT = 2;

    FragmentRecordScoreBinding binding;
    ScoreRecyclerViewAdapter mScoreRecyclerViewAdapter;
    static GolfCourse mGolfCourse;
    private ArrayList<Score> mScoreList;

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
        mScoreList = mScoreRecyclerViewAdapter.getScoreList();
        binding.rvScoreList.setAdapter(mScoreRecyclerViewAdapter);

        binding.tvTotalPar.setText(getTotal(PAR));
        binding.tvTotalScore.setText(getTotal(SCORE));
        binding.tvTotalPutt.setText(getTotal(PUTT));

        return binding.getRoot();
    }

    private String getTotal(int content) {
        int total = 0;
        switch (content) {
            case PAR:
                for (Score score : mScoreList)
                    total += Integer.valueOf(score.getPar());
                break;
            case SCORE:
                for (Score score : mScoreList)
                    total += Integer.valueOf(score.getPoint());
                break;
            case PUTT:
                for (Score score : mScoreList)
                    total += Integer.valueOf(score.getPutt());
                break;
        }
        return String.valueOf(total);
    }

}
