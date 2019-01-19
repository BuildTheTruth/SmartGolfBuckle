package com.golflog.smartgolfbuckle;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.golflog.smartgolfbuckle.databinding.FragmentScoreBinding;
import com.golflog.smartgolfbuckle.vo.GolfCourse;

public class ScoreFragment extends Fragment {
    FragmentScoreBinding binding;
    static GolfCourse mGolfCourse;

    public ScoreFragment() {

    }

    public static ScoreFragment newInstance() {
        return new ScoreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false);
        return binding.getRoot();
    }
}
