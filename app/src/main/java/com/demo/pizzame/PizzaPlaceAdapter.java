package com.demo.pizzame;

import android.content.Context;
import android.content.Intent;
//import android.databinding.DataBindingUtil;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.pizzame.databinding.ListRowBinding;
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
        ListRowBinding listRowBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_row,
                parent,
                false);
        return new PizzaPlaceHolder(listRowBinding);
    }

    @Override
    public void onBindViewHolder(PizzaPlaceHolder holder, int position) {
        ListRowBinding postBinding = holder.binding;
        postBinding.setViewModel(new ListRowViewModel(mContext, mResultData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mResultData == null ? 0 : mResultData.size();
    }

    public class PizzaPlaceHolder extends RecyclerView.ViewHolder {
        private ListRowBinding binding;

        public PizzaPlaceHolder(ListRowBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }
    }
}
