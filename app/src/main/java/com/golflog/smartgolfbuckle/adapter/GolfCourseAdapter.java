package com.golflog.smartgolfbuckle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.databinding.ItemCourseBinding;
import com.golflog.smartgolfbuckle.vo.GolfCourse;

import java.util.ArrayList;

public class GolfCourseAdapter extends RecyclerView.Adapter {
    private ArrayList<GolfCourse> mGolfCourseList;
    private Context context;

    public GolfCourseAdapter(ArrayList<GolfCourse> mGolfCourseList, Context context) {
        this.mGolfCourseList = mGolfCourseList;
        this.context= context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mHolder;
        ItemCourseBinding binding = ItemCourseBinding.inflate(LayoutInflater.from(context), parent, false);
        mHolder = new GolfCourseAdapter.CourseHolder(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CourseHolder itemViewHolder= (CourseHolder) holder;
        ItemCourseBinding binding = itemViewHolder.binding;

        binding.tvCourseName.setText(mGolfCourseList.get(position).getName());
        binding.tvCourseDate.setText(mGolfCourseList.get(position).getDate());
        binding.tvCourseScore.setText(Integer.toString(mGolfCourseList.get(position).getShotDataList().size()));
        binding.layoutBackground.getBackground().setAlpha(80);
    }

    @Override
    public int getItemCount() {
        return mGolfCourseList.size();
    }

    private class CourseHolder extends RecyclerView.ViewHolder {
        ItemCourseBinding binding;

        CourseHolder(ItemCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
