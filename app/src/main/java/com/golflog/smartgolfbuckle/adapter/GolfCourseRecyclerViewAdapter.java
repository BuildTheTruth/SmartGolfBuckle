package com.golflog.smartgolfbuckle.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.GolfCourseActivity;
import com.golflog.smartgolfbuckle.databinding.ItemCourseBinding;
import com.golflog.smartgolfbuckle.vo.GolfCourse;

import java.util.ArrayList;

public class GolfCourseRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<GolfCourse> mGolfCourseList;
    private Context context;

    public GolfCourseRecyclerViewAdapter(ArrayList<GolfCourse> mGolfCourseList, Context context) {
        this.mGolfCourseList = mGolfCourseList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mHolder;
        ItemCourseBinding binding = ItemCourseBinding.inflate(LayoutInflater.from(context), parent, false);
        mHolder = new GolfCourseRecyclerViewAdapter.CourseHolder(binding);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CourseHolder itemViewHolder = (CourseHolder) holder;
        ItemCourseBinding binding = itemViewHolder.binding;

        binding.tvCourseName.setText(mGolfCourseList.get(position).getName());
        binding.tvCourseDate.setText(mGolfCourseList.get(position).getDate());
        binding.tvCourseScore.setText(String.valueOf(mGolfCourseList.get(position).getShotDataList().size()));
        binding.layoutCourse.getBackground().setAlpha(80);
        binding.layoutCourse.setOnClickListener(new CourseLayoutClickListener(position));
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

    private class CourseLayoutClickListener implements View.OnClickListener {
        private int position;

        public CourseLayoutClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, GolfCourseActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("SELECTED_COURSE", mGolfCourseList.get(position));
            context.startActivity(intent);
        }
    }
}
