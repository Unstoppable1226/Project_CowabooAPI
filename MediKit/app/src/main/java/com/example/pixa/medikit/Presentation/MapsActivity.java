package com.example.pixa.medikit.Presentation;

import android.content.pm.PackageManager;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pixa.medikit.Business.PermissionUtils;
import com.example.pixa.medikit.Business.Position;
import com.example.pixa.medikit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MapsActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener, OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {

        /**
         * Request code for location permission request.
         *
         * @see #onRequestPermissionsResult(int, String[], int[])
         */

        /* Constants */
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
        private static final String PDC = "Permanence de Carouge";
        private static final double PDC_LAT = 46.186498;
        private static final double PDC_LNG = 6.142422;

        private int MAX_LENGTH;

        private Position myPos;
        private LatLng myPosDet;
        private ArrayList<Position> arrayPos = new ArrayList<>();
        private MenuItem itemSelected;

        /*Default position*/
        private Position hug;
        private Position cctm;
        private Position cdr;
        private Position pdc;


        /*private Position test;
        private static final String TEST = "Test";
        private static final double TEST_LAT = 46.17983;
        private static final double TEST_LNG = 6.138825;*/

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
                myPos = new Position("Me", 46.174817, 6.139748);
                myPosDet = new LatLng(myPos.getLat(), myPos.getLng());
        }


        private void lookForNearest(){
                double resLat = 0;
                double resLng = 0;
                double resFinal = 0;
                double[] tabRes = new double[MAX_LENGTH];
                int i = 0;
                double resBest = 100;
                for(Position p : arrayPos){
                        if((p.getLat() > 0 && p.getLng() > 0) || (p.getLat() < 0 && p.getLng() < 0)){
                                resLat = p.getLat() - myPos.getLat();
                                resLng = p.getLng() - myPos.getLng();
                        } else {
                                resLat = p.getLat() + myPos.getLat();
                                resLng = p.getLng() + myPos.getLng();
                        }
                        resFinal = resLat + resLng;
                        tabRes[i++] = resFinal;
                        System.out.println(resFinal + " " + p.getName());
                }
                i = 0;
                for(double d : tabRes){
                        if(resBest > d){
                                resBest = d;
                                i++;
                        }
                }
                System.out.println(resBest + " " + arrayPos.get(i));
                LatLng latLng = new LatLng(arrayPos.get(i).getLat(), arrayPos.get(i).getLng());
                putMarker(arrayPos.get(i).getName(), latLng);

        }

        private void setPositions(){
                hug = new Position(HUG, HUG_LAT, HUG_LNG);
                cctm = new Position(CCTM, CCTM_LAT, CCTM_LNG);
                cdr = new Position(CDR, CDR_LAT, CDR_LNG);
                pdc = new Position(PDC, PDC_LAT, PDC_LNG);
                //test = new Position(TEST, TEST_LAT, TEST_LNG);

                arrayPos.add(hug);
                arrayPos.add(cctm);
                arrayPos.add(cdr);
                arrayPos.add(pdc);
                //arrayPos.add(test);

                MAX_LENGTH = arrayPos.size();
        }

        private void setMarkers(){
                LatLng latlngHug = new LatLng(hug.getLat(), hug.getLng());
                LatLng latlngCctm = new LatLng(cctm.getLat(), cctm.getLng());
                LatLng latlngCdr = new LatLng(cdr.getLat(), cdr.getLng());
                LatLng latlngPdc = new LatLng(pdc.getLat(), pdc.getLng());
                //LatLng latlngTest = new LatLng(test.getLat(), test.getLng());

                putMarker(HUG,latlngHug);
                putMarker(CCTM, latlngCctm);
                putMarker(CDR, latlngCdr);
                putMarker(PDC, latlngPdc);
                //putMarker(TEST, latlngTest);
        }

        @Override
        public void onMapReady(GoogleMap map) {
                mMap = map;

                mMap.setOnMyLocationButtonClickListener(this);
                LatLng geneva = new LatLng(46.204391, 6.143158);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geneva, 13));

                enableMyLocation();
                LatLng myPosDet = new LatLng(myPos.getLat(), myPos.getLng());
                putMarker("Ma position", myPosDet);
                setPositions();

        }

        private void putMarker(String name, LatLng latlng){
                mMap.addMarker(new MarkerOptions().position(latlng).title(name));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
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

        public boolean onOptionsItemSelected(MenuItem item){
                if (itemSelected == null){
                        itemSelected = item;
                } else if(!itemSelected.toString().equals(item.toString())){
                        itemSelected.setEnabled(true);
                }
                itemSelected = item;
                item.setEnabled(false);
                mMap.clear();
                putMarker("Ma position", myPosDet);
                if(itemSelected.toString().equals(getString(R.string.action_find_nearest))){
                        System.out.println("find nearest");
                        lookForNearest();

                } else if(itemSelected.toString().equals(getString(R.string.action_find_all))){
                        System.out.println("find all");
                        setMarkers();
                }
                return false;
        }

        public boolean onCreateOptionsMenu (Menu menu) {
                getMenuInflater().inflate(R.menu.map_menu, menu);
                return true;
        } // onCreateOptionsMenu

}
