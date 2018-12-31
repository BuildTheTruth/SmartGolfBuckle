package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.golflog.smartgolfbuckle.databinding.ActivityFindPasswordBinding;

public class FindPasswordActivity extends AppCompatActivity {

    ActivityFindPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_password);
    }
}
