package com.golflog.smartgolfbuckle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.databinding.ItemShotDataBinding;
import com.golflog.smartgolfbuckle.vo.ShotData;

import java.util.ArrayList;


public class ShotDataRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<ShotData> mShotDataList;

    public ShotDataRecyclerViewAdapter(ArrayList<ShotData> mShotDataList, Context context) {
        this.mShotDataList = mShotDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mHolder;
        ItemShotDataBinding binding = ItemShotDataBinding.inflate(LayoutInflater.from(context), parent, false);
        mHolder = new ShotDataRecyclerViewAdapter.ShotDataHolder(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShotDataHolder itemViewHolder = (ShotDataHolder) holder;
        ItemShotDataBinding binding = itemViewHolder.binding;
        ShotData mShotData = mShotDataList.get(position);

        binding.tvShotNum.setText(Integer.toString(position + 1));
        binding.tvShotGolfClub.setText(mShotData.getShotGolfClub().getKind());
        binding.tvLatitude.setText(mShotData.getLatitude());
        binding.tvLongitude.setText(mShotData.getLongitude());
        binding.tvAltitude.setText(mShotData.getAltitude());
        binding.tvDistance.setText(mShotData.getDistance());
        binding.tvAltDifference.setText(mShotData.getAltDifference());
    }

    @Override
    public int getItemCount() {
        return mShotDataList.size();
    }

    public class ShotDataHolder extends RecyclerView.ViewHolder {
        ItemShotDataBinding binding;

        public ShotDataHolder(ItemShotDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
