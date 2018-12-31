package com.golflog.smartgolfbuckle.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import com.golflog.smartgolfbuckle.R;
import com.golflog.smartgolfbuckle.TagRecognitionActivity;
import com.golflog.smartgolfbuckle.databinding.ItemClubBinding;
import com.golflog.smartgolfbuckle.vo.GolfClub;

import java.util.ArrayList;

public class GolfClubAdapter extends RecyclerView.Adapter {
    private String[] shafts = {"Graphite", "Steel"};
    private ArrayList<GolfClub> mGolfClubList;
    private Context context;
    private int changedPosition;

    public GolfClubAdapter(ArrayList<GolfClub> mGolfClubList, Context context, int changedPosition) {
        this.mGolfClubList = mGolfClubList;
        this.context = context;
        this.changedPosition = changedPosition;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mHolder;
        ItemClubBinding binding = ItemClubBinding.inflate(LayoutInflater.from(context), parent, false);
        mHolder = new ClubHolder(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final int tagPosition = position;
        ClubHolder itemViewHolder = (ClubHolder) holder;
        ItemClubBinding binding = itemViewHolder.binding;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.custom_shaft_dropdown_item_line, shafts);
        binding.spClubShaft.setAdapter(adapter);

        binding.tvClubNum.setText(Integer.toString(position + 1));
        binding.tvClubKind.setText(mGolfClubList.get(position).getKind());
        binding.tvClubTag.setText(mGolfClubList.get(position).getTag());
        binding.etClubMaker.setText(mGolfClubList.get(position).getMaker());
        binding.spClubShaft.setSelection(getShaftPosition(mGolfClubList.get(position).getShaft()));

        binding.tvClubTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TagRecognitionActivity.class);
                intent.putExtra("TAG_POSITION", tagPosition);
                context.startActivity(intent);
            }
        });

        if(changedPosition == position)
            binding.tvClubTag.setTextColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return mGolfClubList.size();
    }

    private class ClubHolder extends RecyclerView.ViewHolder {
        ItemClubBinding binding;

        ClubHolder(ItemClubBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private int getShaftPosition(String shaft) {
        for (int i = 0; i < shafts.length; i++)
            if (shaft.equals(shafts[i]))
                return i;
        return 0;
    }
}

