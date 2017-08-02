package com.demo.pizzame;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.view.View;

import com.demo.pizzame.PlaceDetailsActivity;
import com.demo.pizzame.model.Result;

/**
 * Created by Surabhi on 8/1/17.
 */

public class PlaceDetailViewModel extends BaseObservable {
    private Context context;
    private Result mPlaceDetails;

    public PlaceDetailViewModel(Context context, Result result) {
        this.context = context;
        this.mPlaceDetails = result;
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

    public float getAverageRating() {
        return  mPlaceDetails.getRating().getAverageRating().equals("NaN")  ?  0:  Float.parseFloat(mPlaceDetails.getRating().getAverageRating());
    }

    public String getWebAddress() {
        return mPlaceDetails.getBusinessClickUrl();
    }

    public String getDistance() {
        return mPlaceDetails.getDistance() + " miles";
    }

    public View.OnClickListener onClickWebAddress() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mPlaceDetails.getBusinessClickUrl()));
                context.startActivity(browserIntent);
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
                if (addressIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(addressIntent);
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
                context.startActivity(mapIntent);
            }
        };
    }
    public View.OnClickListener onClickCall() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPlaceDetails.getPhone()));
                context.startActivity(intent);
            }
        };
    }
}
