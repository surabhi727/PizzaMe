package com.demo.pizzame;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import com.demo.pizzame.model.Result;

import java.io.Serializable;


public class ListRowViewModel extends BaseObservable {
    private Context context;
    private Result result;

    public ListRowViewModel(Context context, Result result) {
        this.context = context;
        this.result = result;
    }

    public String getPlaceName() {
        return result.getTitle();
    }

    public String getAddress() {
        return result.getFullAddress();
    }

    public String getContactNumber() {
        return result.getPhone();
    }

    public View.OnClickListener onClickPlace() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(context, PlaceDetailsActivity.class);
                intent.putExtra(PlaceDetailsActivity.DETAILS_KEY, result);
                context.startActivity(intent);
            }
        };
    }
}
