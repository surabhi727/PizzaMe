package com.demo.pizzame;

import android.*;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.demo.pizzame.model.QueryResponse;
import com.demo.pizzame.model.Result;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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


    private static final String YAHOO_LOCAL_SEARCH_API = "https://query.yahooapis.com";
    private static final int REQUEST_CHECK_SETTINGS = 0;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static String query = "SELECT * FROM local.search WHERE latitude = \"%f\" and longitude = \"%f\" and query = \"pizza\"";
    private static final String TAG = MainActivity.class.getName();
    private QueryResponse mQueryResponse;
    private TextView mTextView;
  //  private FusedLocationProviderClient mFusedLocationProviderClient;
    //LocationRequest mLocationRequest;
    private RecyclerView mRecyclerView;
    private PizzaPlaceAdapter mPizzaPlaceAdapter;
    private List<Result> pizzaPlacesResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //    mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mPizzaPlaceAdapter = new PizzaPlaceAdapter(this, pizzaPlacesResult );
        mRecyclerView.setAdapter(mPizzaPlaceAdapter);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            return;
        } else {
            init();
        }
    }

    private void updateNearByPizzaPlaces(double latitude, double longitude) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (isNetworkAvailable()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(YAHOO_LOCAL_SEARCH_API)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            LocalSearchAPI localSearchAPI = retrofit.create(LocalSearchAPI.class);
            Call<QueryResponse> call = localSearchAPI.getNearByPizzaPlaces(String.format(query, latitude, longitude), "json","distance");
            call.enqueue(new Callback<QueryResponse>() {
                @Override
                public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "value" + response.isSuccessful());
                        mQueryResponse = response.body();
                        pizzaPlacesResult.clear();
                        pizzaPlacesResult.addAll(Arrays.asList(mQueryResponse.getQuery().getResults().getResult()));
                        mPizzaPlaceAdapter.notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "response unsuccesful" + response.message());
                        //           alertUserAboutError(getString(R.string.error_message));
                    }
                }

                @Override
                public void onFailure(Call<QueryResponse> call, Throwable t) {
                    Log.e(TAG, "Failure " + t.getMessage());
                }
            });
        } else {
            Log.e(TAG, "network unavailable");
            //         alertUserAboutError(getString(R.string.network_unavailable_message));
        }

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
                return;
            }
        }
    }

    private void init() {

//        mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null) {
//
//                }
//            }
//        });
        updateNearByPizzaPlaces(40.54, -74.30);
    }
}
