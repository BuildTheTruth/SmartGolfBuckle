package com.golflog.smartgolfbuckle;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.golflog.smartgolfbuckle.api.RouteEvaluator;
import com.golflog.smartgolfbuckle.databinding.ActivityDetailRecordBinding;
import com.golflog.smartgolfbuckle.vo.ShotData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class DetailRecordActivity extends AppCompatActivity implements OnMapReadyCallback {
    private final int IMAGE_SELECT = 1001;
    ActivityDetailRecordBinding binding;

    ArrayList<ShotData> mShotDataList;
    HashMap<String, ShotData> shotDataHashMap;
    MapAnimator mapAnimator;
    List<LatLng> routes = new ArrayList<>();
    int markerIndex = 0;
    boolean isEndedAnimation = false;
    GoogleMap mGoogleMap;

    View shotView;
    View flagView;
    TextView tvCount;

    Dialog shotDialog;
    Dialog adDialog;
    TextView tvClubKind;
    TextView tvDistance;
    TextView tvAltDifference;
    ImageView ivAdFromShot;

    ShotData markedShotData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_record);

        shotDialog = new Dialog(DetailRecordActivity.this);
        shotDialog.setContentView(R.layout.dialog_shot);
        shotDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        shotDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                adDialog.show();
            }
        });
        tvClubKind = (TextView) shotDialog.findViewById(R.id.tv_clubKind);
        tvDistance = (TextView) shotDialog.findViewById(R.id.tv_distance);
        tvAltDifference = (TextView) shotDialog.findViewById(R.id.tv_altDifference);

        adDialog = new Dialog(DetailRecordActivity.this);
        adDialog.setContentView(R.layout.dialog_ad);
        adDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (!isEndedAnimation) {
                    startLineAnimation(++markerIndex);
                }
            }
        });

        ivAdFromShot = (ImageView) adDialog.findViewById(R.id.iv_adFromShot);
        ivAdFromShot.setOnClickListener(new AdImageClickListener());

        shotDataHashMap = new HashMap<>();
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
        String markerId;

        setShotMarkerView();
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        for (int idx = 0; idx < mShotDataList.size() - 1; idx++) {
            ShotData shot = mShotDataList.get(idx);
            Marker marker = addShotMarker(shot, idx);
            markerId = marker.getId();
            shotDataHashMap.put(markerId, shot);
        }

        for (int idx = 0; idx < mShotDataList.size(); idx++) {
            ShotData shot = mShotDataList.get(idx);
            routes.add(shot.getPosition());
        }

        ShotData flag = mShotDataList.get(mShotDataList.size() - 1);
        markerId = addFlagMarker(flag).getId();
        shotDataHashMap.put(markerId, flag);
        mLatLng = flag.getPosition();
        mCameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 16);
        mGoogleMap.animateCamera(mCameraUpdate);
        mGoogleMap.setOnMarkerClickListener(new MarkerClickListener());
        startLineAnimation(markerIndex);
    }

    private void startLineAnimation(int index) {
        if (index == routes.size() - 1) {
            isEndedAnimation = true;
            return;
        }
        mapAnimator = new MapAnimator();
        List<LatLng> bangaloreRoute = new ArrayList<>();
        bangaloreRoute.add(routes.get(index));
        bangaloreRoute.add(routes.get(index + 1));
        mapAnimator.animateRoute(mGoogleMap, bangaloreRoute, mShotDataList.get(index));
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
        markerOptions.snippet(String.valueOf(idx + 1));
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, shotView)));

        return mGoogleMap.addMarker(markerOptions);
    }

    private void addPolyline(ShotData shot1, ShotData shot2) {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.add(shot1.getPosition(), shot2.getPosition());
        polylineOptions.width(5);
        polylineOptions.color(Color.RED);
        mGoogleMap.addPolyline(polylineOptions);
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

    private class MarkerClickListener implements GoogleMap.OnMarkerClickListener {
        @Override
        public boolean onMarkerClick(Marker marker) {
            String markerId = marker.getId();
            markedShotData = shotDataHashMap.get(markerId);
            if (markedShotData.getShotGolfClub() == null)
                return false;

            showShotDialog(markedShotData);
            return false;
        }
    }

    private class AdImageClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intent, "광고 이미지을 선택하세요."), IMAGE_SELECT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode == IMAGE_SELECT) {
                Uri uri = data.getData();
                String adImage = uri.toString();
                markedShotData.setAdImage(adImage);
                Picasso.get().load(markedShotData.getAdImage()).into(ivAdFromShot);
            }
        } catch (NullPointerException ex) {
            adDialog.dismiss();
        }
    }

    private void showShotDialog(ShotData shot) {
        tvClubKind.setText(shot.getShotGolfClub().getKind());
        tvDistance.setText(String.format(Locale.KOREA, "%s m", shot.getDistance()));
        tvAltDifference.setText(String.format(Locale.KOREA, "%s m", shot.getAltDifference()));
        if (shot.getAdImage() != null)
            Picasso.get().load(shot.getAdImage()).into(ivAdFromShot);
        else
            ivAdFromShot.setImageResource(R.drawable.ad_hyundai);

        shotDialog.show();
    }

    private class MapAnimator {
        private Polyline foregroundPolyline;
        private PolylineOptions optionsForeground;
        private AnimatorSet firstRunAnimSet;
        private int startDelay;

        private void animateRoute(GoogleMap googleMap, List<LatLng> bangaloreRoute, final ShotData shot) {
            if (firstRunAnimSet == null) {
                firstRunAnimSet = new AnimatorSet();
            } else {
                firstRunAnimSet.removeAllListeners();
                firstRunAnimSet.end();
                firstRunAnimSet.cancel();

                firstRunAnimSet = new AnimatorSet();
            }

            //Reset the polylines
            if (foregroundPolyline != null) foregroundPolyline.remove();

            if (markerIndex == 0)
                startDelay = 2000;
            else
                startDelay = 0;

            optionsForeground = new PolylineOptions().add(bangaloreRoute.get(0)).color(Color.RED).width(5);
            foregroundPolyline = googleMap.addPolyline(optionsForeground);

            ObjectAnimator foregroundRouteAnimator = ObjectAnimator.ofObject(this, "routeIncreaseForward", new RouteEvaluator(), bangaloreRoute.toArray());
            foregroundRouteAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            foregroundRouteAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    showShotDialog(shot);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            firstRunAnimSet.play(foregroundRouteAnimator);
            firstRunAnimSet.setStartDelay(startDelay);
            firstRunAnimSet.setDuration(1600);
            firstRunAnimSet.start();
        }

        // This is an handy method to call if you want to remove the polyline because of some condition like back press
        void stopAndRemovePolyLine() {
            if (mapAnimator != null) {
                foregroundPolyline.remove();
                firstRunAnimSet.cancel();
            }
        }

        /**
         * This will be invoked by the ObjectAnimator multiple times. Mostly every 16ms.
         **/
        public void setRouteIncreaseForward(LatLng endLatLng) {
            List<LatLng> foregroundPoints = foregroundPolyline.getPoints();
            foregroundPoints.add(endLatLng);
            foregroundPolyline.setPoints(foregroundPoints);
        }
    }
}
