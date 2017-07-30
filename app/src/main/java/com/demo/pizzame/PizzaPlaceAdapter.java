package com.demo.pizzame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.pizzame.model.Result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Surabhi on 7/29/17.
 */

public class PizzaPlaceAdapter extends RecyclerView.Adapter<PizzaPlaceAdapter.PizzaPlaceHolder> {

    private List<Result> mResultData;
    private Context mContext;
    public PizzaPlaceAdapter(Context context, List<Result> resultData) {
        this.mResultData = resultData;
        this.mContext = context;
    }

    @Override
    public PizzaPlaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        PizzaPlaceHolder holder = new PizzaPlaceHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PizzaPlaceHolder holder, int position) {
        holder.bindPizzaPlaceDetails(mResultData.get(position));
    }

    @Override
    public int getItemCount() {
        return mResultData == null ? 0 : mResultData.size();
    }

    public class PizzaPlaceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameLabel;
        private TextView addressLabel;
        private TextView phoneNumLabel;

        public PizzaPlaceHolder(View itemView) {
            super(itemView);
            nameLabel = (TextView) itemView.findViewById(R.id.name);
            addressLabel = (TextView) itemView.findViewById(R.id.address);
            phoneNumLabel = (TextView) itemView.findViewById(R.id.phonenum);
            itemView.setOnClickListener(this);
        }

        public void bindPizzaPlaceDetails(Result result) {
            nameLabel.setText(result.getTitle());
            addressLabel.setText(result.getFullAddress());
            phoneNumLabel.setText(result.getPhone());
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(mContext, PlaceDetailsActivity.class);
            intent.putExtra(PlaceDetailsActivity.DETAILS_KEY, (Serializable) mResultData.get(position));
            mContext.startActivity(intent);
            Toast.makeText(mContext,"Hello",Toast.LENGTH_SHORT).show();
        }
    }
}
