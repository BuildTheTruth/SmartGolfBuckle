package com.golflog.smartgolfbuckle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.databinding.ItemScoreBinding;
import com.golflog.smartgolfbuckle.vo.Score;
import com.golflog.smartgolfbuckle.vo.ShotData;

import java.util.ArrayList;

public class ScoreRecyclerViewAdapter extends RecyclerView.Adapter {
    private final int MAX_HOLE_NUMBER = 18;
    private Context context;
    private ArrayList<Score> mScoreList;

    public ScoreRecyclerViewAdapter(ArrayList<ShotData> mShotDataList, Context context) {
        this.context = context;
        setScoreListByShotDataList(mShotDataList);
        mScoreList = new ArrayList<>(MAX_HOLE_NUMBER);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mHolder;
        ItemScoreBinding binding = ItemScoreBinding.inflate(LayoutInflater.from(context), parent, false);
        mHolder = new ScoreRecyclerViewAdapter.ScoreHolder(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScoreHolder itemViewHolder = (ScoreHolder) holder;
        ItemScoreBinding binding = itemViewHolder.binding;


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ScoreHolder extends RecyclerView.ViewHolder {
        ItemScoreBinding binding;

        public ScoreHolder(ItemScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void setScoreListByShotDataList(ArrayList<ShotData> mShotDataList) {
        ArrayList<ArrayList<ShotData>> mShotDataListByHole = new ArrayList<>(MAX_HOLE_NUMBER);
        for(int i=0; i<MAX_HOLE_NUMBER; i++)
            mShotDataListByHole.set(i, new ArrayList<ShotData>());



    }
}
