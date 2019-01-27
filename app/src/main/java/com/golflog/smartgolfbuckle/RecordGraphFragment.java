package com.golflog.smartgolfbuckle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecordGraphFragment extends Fragment {
    public RecordGraphFragment() {

    }

    public static RecordGraphFragment newInstance() {
        return new RecordGraphFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_record_grpah, container, false);
    }
}
