package com.demo.pizzame;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.pizzame.model.PizzaPlace;
import com.demo.pizzame.model.QueryResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String YAHOO_LOCAL_SEARCH_API = "https://query.yahooapis.com";
    private static final String QUERY = "SELECT * FROM local.search WHERE latitude = \"%f\" and longitude = \"%f\" and query = \"pizza\"";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private RecyclerView mPlaceList;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PizzaPlaceAdapter mPizzaPlaceAdapter;
    private List<PizzaPlace> mPizzaPlacesResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mPlaceList = (RecyclerView) findViewById(R.id.recycler_view);
        mErrorMessage = (TextView) findViewById(R.id.errorMessage);
        mPlaceList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mPizzaPlaceAdapter = new PizzaPlaceAdapter(this, mPizzaPlacesResult);
        mPlaceList.setAdapter(mPizzaPlaceAdapter);

        // check for location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    finish();
                }
            }
        }
    }

    private void init() {
        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    updateNearByPizzaPlaces(location.getLatitude(), location.getLongitude());
                } else {
                    mProgressBar.setVisibility(View.GONE);
                    mErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void updateNearByPizzaPlaces(double latitude, double longitude) {
        if (isNetworkAvailable()) {
            mPlaceList.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(YAHOO_LOCAL_SEARCH_API)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            LocalSearchAPI localSearchAPI = retrofit.create(LocalSearchAPI.class);
            Call<QueryResponse> call =
                    localSearchAPI.getNearByPizzaPlaces(String.format(QUERY, latitude, longitude));
            call.enqueue(new Callback<QueryResponse>() {
                @Override
                public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                    mProgressBar.setVisibility(View.GONE);
                    mErrorMessage.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        updatePlaceList(response.body().getQuery().getResults().getPlaceList());
                    } else {
                        Log.e(TAG, "Response unsuccessful" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<QueryResponse> call, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                    Log.e(TAG, "Failure " + t.getMessage());
                }
            });
        } else {
            Log.e(TAG, "Network unavailable");
        }
    }

    private void updatePlaceList(PizzaPlace[]  placeList) {
        mPlaceList.setVisibility(View.VISIBLE);
        mPizzaPlacesResult.clear();
        mPizzaPlacesResult.addAll(Arrays.asList(placeList));
        mPizzaPlaceAdapter.notifyDataSetChanged();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
