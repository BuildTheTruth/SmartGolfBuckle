package com.golflog.smartgolfbuckle;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golflog.smartgolfbuckle.databinding.FragmentDetailMapBinding;
import com.golflog.smartgolfbuckle.vo.ShotData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class DetailMapFragment extends Fragment implements OnMapReadyCallback {
    FragmentDetailMapBinding binding;
    private static ArrayList<ShotData> mShotDataList;
    MapView mMapView;

    public DetailMapFragment() {

    }

    public static DetailMapFragment newInstance(ArrayList<ShotData> shotDataList) {
        mShotDataList = shotDataList;
        return new DetailMapFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_map, container, false);
        mMapView = binding.mapShotData;
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        return binding.getRoot();
    }



    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        mMapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
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
            mCameraUpdate = CameraUpdateFactory.newLatLng(mLatLng);
            googleMap.animateCamera(mCameraUpdate);
            googleMap.addMarker(new MarkerOptions()
                    .position(mLatLng)
                    .title(Integer.toString(idx)));
        }
    }
}
