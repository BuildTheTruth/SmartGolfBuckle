package com.golflog.smartgolfbuckle;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.golflog.smartgolfbuckle.databinding.ActivityDetailRecordBinding;
import com.golflog.smartgolfbuckle.vo.ShotData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class DetailRecordActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<ShotData> mShotDataList;
    ActivityDetailRecordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_record);
        int hole = getIntent().getIntExtra("SELECTED_HOLE", 0);
        mShotDataList = getIntent().getParcelableArrayListExtra("SELECTED_SHOT_DATA");

        binding.toolbar.setTitle("H" + hole);
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng mLatLng;
        CameraUpdate mCameraUpdate;
        float latitude, longitude;
        for (int idx = 0; idx < mShotDataList.size(); idx++) {
            ShotData sd = mShotDataList.get(idx);
            latitude = Float.parseFloat(sd.getLatitude());
            longitude = Float.parseFloat(sd.getLongitude());
            mLatLng = new LatLng(latitude, longitude);
            mCameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 16);
            googleMap.animateCamera(mCameraUpdate);
            googleMap.addMarker(new MarkerOptions()
                    .position(mLatLng)
                    .title(Integer.toString(idx + 1)));
        }
    }
}
