package com.demo.pizzame;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.demo.pizzame.databinding.ActivityPlaceDetailsBinding;
import com.demo.pizzame.model.PizzaPlace;
import com.demo.pizzame.viewModel.PlaceDetailViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PlaceDetailsActivity extends AppCompatActivity implements OnMapReadyCallback, PlaceDetailViewModel.RequestCallPermissionInterface {

    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    public static final String DETAILS_KEY = "DETAILS_KEY";

    private static final String TAG = PlaceDetailsActivity.class.getSimpleName();

    private PlaceDetailViewModel mPlaceDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!getIntent().hasExtra(DETAILS_KEY)) {
            Log.e(TAG, "Missing PlaceDetails to display.");
            finish();
        }

        ActivityPlaceDetailsBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_place_details);

        PizzaPlace placeDetails  = (PizzaPlace) getIntent().getParcelableExtra(DETAILS_KEY);
        mPlaceDetailViewModel = new PlaceDetailViewModel(this, placeDetails, this);
        dataBinding.setPlaceDetailViewModel(mPlaceDetailViewModel);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng = mPlaceDetailViewModel.getPlaceLatLng();
        googleMap.addMarker(
                new MarkerOptions().position(latLng).title(mPlaceDetailViewModel.getPlaceName()));
        CameraPosition cameraPosition = CameraPosition.builder().target(latLng).zoom(16).build();
        googleMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition), 1500, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPlaceDetailViewModel.makeCall();
                } else {
                    Toast.makeText(PlaceDetailsActivity.this,
                            R.string.toast_permission_denied,
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void requestCallPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.CALL_PHONE},
                MY_PERMISSIONS_REQUEST_CALL_PHONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
