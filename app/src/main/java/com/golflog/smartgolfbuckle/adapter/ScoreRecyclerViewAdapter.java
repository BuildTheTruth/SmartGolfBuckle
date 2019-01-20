package com.golflog.smartgolfbuckle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.golflog.smartgolfbuckle.databinding.ItemScoreBinding;
import com.golflog.smartgolfbuckle.vo.Score;
import com.golflog.smartgolfbuckle.vo.ShotData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class ScoreRecyclerViewAdapter extends RecyclerView.Adapter {
    private int nHole;
    private Context context;
    private ArrayList<Score> mScoreList;

    public ScoreRecyclerViewAdapter(ArrayList<ShotData> mShotDataList, Context context) {
        this.context = context;
        mScoreList = new ArrayList<>();
        setScoreListByShotDataList(mShotDataList);
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
        Score mScore = mScoreList.get(position);

        if (mScore != null) {
            binding.tvHole.setText(Integer.toString(position + 1));
            binding.tvPar.setText(mScore.getPar());
            binding.tvScore.setText(mScore.getPoint());
            binding.tvPutt.setText(mScore.getPutt());
            binding.tvGir.setText(mScore.getGir());
            binding.tvFwhit.setText(mScore.getFwhit());
        }
    }

    @Override
    public int getItemCount() {
        return mScoreList.size();
    }

    public class ScoreHolder extends RecyclerView.ViewHolder {
        ItemScoreBinding binding;

        private ScoreHolder(ItemScoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private void setScoreListByShotDataList(ArrayList<ShotData> mShotDataList) {
        SparseArray<ArrayList<ShotData>> mShotDataSparseArray = new SparseArray<>();

        int maxHole = 0;
        for (ShotData sd : mShotDataList)
            if (maxHole < sd.getHole())
                maxHole = sd.getHole();

        nHole = maxHole;
        for (int i = 0; i < nHole; i++) {
            mScoreList.add(null);
            mShotDataSparseArray.put(i, new ArrayList<ShotData>());
        }

        for (ShotData sd : mShotDataList)
            mShotDataSparseArray.get(sd.getHole() - 1).add(sd);

        ArrayList<ShotData> mShotDataByHole;

        int par;
        int point;
        int putt;
        String gir = "○";
        String fwhit = "○";
        Score mScore;
        for (int i = 0; i < nHole; i++) {
            putt = 0;
            mShotDataByHole = mShotDataSparseArray.get(i);
            if (!mShotDataByHole.isEmpty()) {
                par = mShotDataByHole.get(0).getPar();
                point = mShotDataByHole.size() - par;
                for (ShotData sd : mShotDataByHole)
                    if (sd.getShotGolfClub().getKind().equals("PT")) putt++;
                // to do
                // gir
                // fwhit
                mScore = new Score(Integer.toString(par), Integer.toString(point), Integer.toString(putt), gir, fwhit);
                mScoreList.set(i, mScore);
            }
        }

    }
}
