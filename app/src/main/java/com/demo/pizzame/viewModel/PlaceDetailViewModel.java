package com.demo.pizzame.viewModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;

import com.demo.pizzame.model.PizzaPlace;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Surabhi on 8/1/17.
 */

public class PlaceDetailViewModel extends BaseObservable {

    private Context mContext;
    private PizzaPlace mPlaceDetails;
    private RequestCallPermissionInterface mRequestCallPermissionInterface;

    public PlaceDetailViewModel(Context context, PizzaPlace pizzaPlace, RequestCallPermissionInterface requestCallPermissionInterface) {
        mContext = context;
        mPlaceDetails = pizzaPlace;
        mRequestCallPermissionInterface = requestCallPermissionInterface;
    }

    public String getPlaceName() {
        return mPlaceDetails.getTitle();
    }

    public String getAddress() {
        return mPlaceDetails.getFullAddress();
    }

    public String getContactNumber() {
        return mPlaceDetails.getPhone();
    }

    public int getContactNumberVisibility() {
        return TextUtils.isEmpty(mPlaceDetails.getPhone()) ? View.GONE : View.VISIBLE;
    }

    public float getAverageRating() {
        return mPlaceDetails.getRating().getAverageRating().equals("NaN")  ?  0 :
                Float.parseFloat(mPlaceDetails.getRating().getAverageRating());
    }

    public int getAverageRatingVisibility() {
        return mPlaceDetails.getRating() == null
                || mPlaceDetails.getRating().getAverageRating() == null
                || mPlaceDetails.getRating().getAverageRating().equals("NaN") ? View.GONE : View.VISIBLE;
    }

    public String getWebAddress() {
        return mPlaceDetails.getBusinessClickUrl();
    }

    public int getWebAddressVisibility() {
        return TextUtils.isEmpty(mPlaceDetails.getBusinessClickUrl()) ? View.GONE : View.VISIBLE;
    }

    public String getDistance() {
        return mPlaceDetails.getDistance() + " miles";
    }

    public LatLng getPlaceLatLng() {
        return new LatLng(mPlaceDetails.getLatitude(), mPlaceDetails.getLongitude());
    }

    public View.OnClickListener onClickWebAddress() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlaceDetails.getBusinessClickUrl()));
                mContext.startActivity(browserIntent);
            }
        };
    }

    public View.OnClickListener onClickAddressDetails() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri addressUri = Uri.parse("geo:0,0?q="+mPlaceDetails.getLatitudeString()+","+mPlaceDetails.getLongitudeString()+" ("+mPlaceDetails.getTitle()+")");
                Intent addressIntent = new Intent(Intent.ACTION_VIEW, addressUri);
                addressIntent.setPackage("com.google.android.apps.maps");
                if (addressIntent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(addressIntent);
                }
            }
        };
    }

    public View.OnClickListener onClickDrivingDirections() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+ mPlaceDetails.getLatitudeString()+","+mPlaceDetails.getLongitudeString()+"&mode=d");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mContext.startActivity(mapIntent);
            }
        };
    }

    public View.OnClickListener onClickCall() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    makeCall();
                } else {
                    if (mRequestCallPermissionInterface != null) {
                        mRequestCallPermissionInterface.requestCallPermission();
                    }
                }
            }
        };
    }

    public void makeCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPlaceDetails.getPhone()));
        mContext.startActivity(intent);
    }

    public interface RequestCallPermissionInterface {
        void requestCallPermission();
    }
}
