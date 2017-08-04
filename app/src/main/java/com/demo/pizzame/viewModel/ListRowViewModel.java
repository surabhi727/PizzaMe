package com.demo.pizzame.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.demo.pizzame.PlaceDetailsActivity;
import com.demo.pizzame.model.PizzaPlace;

/**
 * Created by Surabhi on 8/2/17.
 */

public class ListRowViewModel extends BaseObservable {
    private Context mContext;
    private PizzaPlace mPizzaPlace;

    public ListRowViewModel(Context context, PizzaPlace pizzaPlace) {
        mContext = context;
        mPizzaPlace = pizzaPlace;
    }

    public String getPlaceName() {
        return mPizzaPlace.getTitle();
    }

    public String getAddress() {
        return mPizzaPlace.getFullAddress();
    }

    public String getContactNumber() {
        return mPizzaPlace.getPhone();
    }

    public String getDistance() {
        return mPizzaPlace.getDistance() + " miles";
    }


    public View.OnClickListener onClickPlace() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(mContext, PlaceDetailsActivity.class);
                intent.putExtra(PlaceDetailsActivity.DETAILS_KEY, mPizzaPlace);
                mContext.startActivity(intent);
            }
        };
    }
}
