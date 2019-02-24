package com.golflog.smartgolfbuckle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.DetailRecordActivity;
import com.golflog.smartgolfbuckle.R;
import com.golflog.smartgolfbuckle.databinding.ItemScoreBinding;
import com.golflog.smartgolfbuckle.vo.Score;
import com.golflog.smartgolfbuckle.vo.ShotData;

import java.util.ArrayList;

public class ScoreRecyclerViewAdapter extends RecyclerView.Adapter {
    private int nHole;
    private Context context;
    private ArrayList<Score> mScoreList;
    SparseArray<ArrayList<ShotData>> mShotDataSparseArray;

    public ScoreRecyclerViewAdapter(ArrayList<ShotData> mShotDataList, Context context) {
        this.context = context;
        mScoreList = new ArrayList<>();
        mShotDataSparseArray = new SparseArray<>();
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
            binding.layoutScore.setOnTouchListener(new ScoreLayoutTouchListener(position, binding));
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
        ArrayList<ShotData> mShotDataByHole;

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
                point = mShotDataByHole.size() - par - 1; // Flag Data 제외
                for (ShotData sd : mShotDataByHole) {
                    if (sd.getShotGolfClub() == null) continue;
                    if (sd.getShotGolfClub().getKind().equals("PT")) putt++;
                }
                // to do
                // gir
                // fwhit
                mScore = new Score(Integer.toString(par), Integer.toString(point), Integer.toString(putt), gir, fwhit);
                mScoreList.set(i, mScore);
            }
        }

    }

    private class ScoreLayoutTouchListener implements View.OnTouchListener {
        int position;
        ItemScoreBinding binding;

        public ScoreLayoutTouchListener(int position, ItemScoreBinding binding) {
            this.position = position;
            this.binding = binding;
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    binding.layoutScore.setBackgroundResource(R.color.colorSelectedItem);
                    break;
                case MotionEvent.ACTION_UP:
                    binding.layoutScore.setBackgroundResource(R.color.white);
                    Intent intent = new Intent(context, DetailRecordActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("SELECTED_SHOT_DATA", mShotDataSparseArray.get(position));
                    intent.putExtra("SELECTED_HOLE", position + 1);
                    context.startActivity(intent);
                    break;
                default:
                    binding.layoutScore.setBackgroundResource(R.color.white);
            }
            return true;
        }
    }

    public ArrayList<Score> getScoreList() {
        return mScoreList;
    }
}
