package com.golflog.smartgolfbuckle;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.golflog.smartgolfbuckle.databinding.ActivityDetailRecordBinding;
import com.golflog.smartgolfbuckle.vo.ShotData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class DetailRecordActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityDetailRecordBinding binding;

    ArrayList<ShotData> mShotDataList;
    GoogleMap mGoogleMap;

    View shotView;
    View flagView;
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_record);

        int hole = getIntent().getIntExtra("SELECTED_HOLE", 0);
        mShotDataList = getIntent().getParcelableArrayListExtra("SELECTED_SHOT_DATA");

        binding.toolbar.setTitle("H" + hole);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        LatLng mLatLng;
        CameraUpdate mCameraUpdate;

        setShotMarkerView();
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        for (int idx = 0; idx < mShotDataList.size() - 1; idx++) {
            ShotData shot = mShotDataList.get(idx);
            addShotMarker(shot, idx);
        }

        ShotData flag = mShotDataList.get(mShotDataList.size() - 1);
        addFlagMarker(flag);
        mLatLng = flag.getPosition();

        for (int idx = 0; idx < mShotDataList.size() - 1; idx++) {
            ShotData shot1 = mShotDataList.get(idx);
            ShotData shot2 = mShotDataList.get(idx + 1);
            addPolyline(shot1, shot2);
        }

        mCameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 16);
        mGoogleMap.animateCamera(mCameraUpdate);
    }

    private void setShotMarkerView() {
        shotView = LayoutInflater.from(this).inflate(R.layout.shot_marker, null);
        flagView = LayoutInflater.from(this).inflate(R.layout.flag_marker, null);
        tvCount = shotView.findViewById(R.id.tv_count);
    }

    private Marker addShotMarker(ShotData shot, int idx) {
        LatLng position = shot.getPosition();
        tvCount.setText(String.valueOf(idx + 1));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, shotView)));

        return mGoogleMap.addMarker(markerOptions);
    }

    private Polyline addPolyline(ShotData shot1, ShotData shot2) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(shot1.getPosition(), shot2.getPosition());
        polylineOptions.width(5);
        polylineOptions.color(Color.RED);
        return mGoogleMap.addPolyline(polylineOptions);
    }

    private Marker addFlagMarker(ShotData flag) {
        LatLng position = flag.getPosition();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, flagView)));

        return mGoogleMap.addMarker(markerOptions);
    }


    private Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private class  MarkerClickListener implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(Marker marker) {
            return false;
        }
    }

}
