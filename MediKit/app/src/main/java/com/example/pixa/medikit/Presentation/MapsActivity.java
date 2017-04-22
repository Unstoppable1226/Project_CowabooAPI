package com.example.pixa.medikit.Presentation;

import android.content.pm.PackageManager;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.pixa.medikit.Business.PermissionUtils;
import com.example.pixa.medikit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;


public class MapsActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

        /**
         * Request code for location permission request.
         *
         * @see #onRequestPermissionsResult(int, String[], int[])
         */
        private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

        private static final String HUG = "HUG";
        private static final double HUG_LAT = 46.193597;
        private static final double HUG_LNG = 6.14902;
        private static final String CCTM = "Centre de Chirurgie et de Thérapie de la main";
        private static final double CCTM_LAT = 46.200122;
        private static final double CCTM_LNG = 6.138158;
        private static final String CDR = "Centre de Dermatologie Rive";
        private static final double CDR_LAT = 46.201643;
        private static final double CDR_LNG = 6.152348;



        /**
         * Flag indicating whether a requested permission has been denied after returning in
         * {@link #onRequestPermissionsResult(int, String[], int[])}.
         */
        private boolean mPermissionDenied = false;

        private GoogleMap mMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_maps);

                SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap map) {
                mMap = map;

                mMap.setOnMyLocationButtonClickListener(this);
                enableMyLocation();

                LatLng hug = new LatLng(HUG_LAT, HUG_LNG);
                LatLng cctm = new LatLng(CCTM_LAT, CCTM_LNG);
                LatLng cdr = new LatLng(CDR_LAT, CDR_LNG);
                putMarker(HUG,hug);
                putMarker(CCTM, cctm);
                putMarker(CDR, cdr);



        }

        private void putMarker(String name, LatLng latlng){
                mMap.addMarker(new MarkerOptions().position(latlng)
                        .title(name));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        }


        /**
         * Enables the My Location layer if the fine location permission has been granted.
         */
        private void enableMyLocation() {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
                        // Permission to access the location is missing.
                        PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                        Manifest.permission.ACCESS_FINE_LOCATION, true);
                } else if (mMap != null) {
                        // Access to the location has been granted to the app.
                        mMap.setMyLocationEnabled(true);
                }
        }

        @Override
        public boolean onMyLocationButtonClick() {
                Toast.makeText(this, String.valueOf(mMap.getMyLocation()), Toast.LENGTH_SHORT).show(); //Normalement ca devrait donner ma position en temps réel
                // Return false so that we don't consume the event and the default behavior still occurs
                // (the camera animates to the user's current position).
                return false;
        }


        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
                if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
                        return;
                }

                if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                        // Enable the my location layer if the permission has been granted.
                        enableMyLocation();
                } else {
                        // Display the missing permission error dialog when the fragments resume.
                        mPermissionDenied = true;
                }
        }

        @Override
        protected void onResumeFragments() {
                super.onResumeFragments();
                if (mPermissionDenied) {
                        // Permission was not granted, display error dialog.
                        showMissingPermissionError();
                        mPermissionDenied = false;
                }
        }

        /**
         * Displays a dialog with error message explaining that the location permission is missing.
         */
        private void showMissingPermissionError() {
                PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
        }

}
