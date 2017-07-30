package com.demo.pizzame;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.pizzame.model.Result;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

public class PlaceDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private AppCompatRatingBar mRatingBar;
    private TextView mPlaceName;
    private MapFragment mMapFragment;
    private Result mPlaceDetails;
    private TextView mAddressDetails;
    public static final String DETAILS_KEY = "DETAILS_KEY";
    private float mPlaceRating;
    private TextView mWebAddress;
    private TextView mPhoneNum;
    private TextView mDistance;
    private RelativeLayout mAddressLayout, mPhoneNumLayout, mDrivingLayout, mWebAddressLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);
        getSupportActionBar().setTitle("Details");
        if(getIntent().hasExtra(DETAILS_KEY)) {
            mPlaceDetails  = (Result) getIntent().getSerializableExtra(DETAILS_KEY);
        }
        mMapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_view);
        mMapFragment.getMapAsync(this);
        mPlaceName = (TextView) findViewById(R.id.place_name);
        mRatingBar = (AppCompatRatingBar) findViewById(R.id.ratingbar);
        mAddressDetails = (TextView) findViewById(R.id.address_details);
        mPhoneNum = (TextView) findViewById(R.id.phone_num);
        mWebAddress = (TextView) findViewById(R.id.web_address);
        mDistance = (TextView) findViewById(R.id.distance);

        mAddressLayout = (RelativeLayout) findViewById(R.id.address_layout);
        mDrivingLayout = (RelativeLayout) findViewById(R.id.driving_layout);
        mPhoneNumLayout = (RelativeLayout) findViewById(R.id.phone_layout);
        mWebAddressLayout = (RelativeLayout) findViewById(R.id.web_address_layout) ;

        mPlaceName.setText(mPlaceDetails.getTitle());
        mPlaceRating = mPlaceDetails.getRating().getAverageRating().equals("NaN")  ? 0: Float.valueOf(mPlaceDetails.getRating().getAverageRating());
        mRatingBar.setRating(mPlaceRating);
        mAddressDetails.setText(mPlaceDetails.getFullAddress());
        mPhoneNum.setText(mPlaceDetails.getPhone());
        mWebAddress.setText(mPlaceDetails.getBusinessClickUrl());
        mDistance.setText(mPlaceDetails.getDistance()+" miles");
        mAddressLayout.setOnClickListener(this);
        mDrivingLayout.setOnClickListener(this);
        mPhoneNumLayout.setOnClickListener(this);
        mWebAddressLayout.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(new LatLng(mPlaceDetails.getLatitude(),mPlaceDetails.getLongitude()))
                .zoom(16)
                .bearing(0)
                .tilt(90)

                .build();
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(mPlaceDetails.getLatitude(),mPlaceDetails.getLongitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 3000, null);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_layout:
                Uri addressUri = Uri.parse("geo:0,0?q="+mPlaceDetails.getLatitudeString()+","+mPlaceDetails.getLongitudeString()+" ("+mPlaceDetails.getTitle()+")");
                Intent addressIntent = new Intent(Intent.ACTION_VIEW, addressUri);
                addressIntent.setPackage("com.google.android.apps.maps");
                if (addressIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(addressIntent);
                }
                break;
            case R.id.phone_layout:

                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    return;
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPlaceDetails.getPhone()));
                    startActivity(intent);
                }

                break;
            case R.id.driving_layout:
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+ mPlaceDetails.getLatitudeString()+","+mPlaceDetails.getLongitudeString()+"&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.web_address_layout:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlaceDetails.getBusinessClickUrl()));
                startActivity(browserIntent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPlaceDetails.getPhone()));
                    startActivity(intent);
                } else {
                    finish();
                }
                return;
            }
        }
    }
}
