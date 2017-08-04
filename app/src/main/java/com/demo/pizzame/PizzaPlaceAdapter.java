package com.demo.pizzame;

import android.content.Context;
//import android.databinding.DataBindingUtil;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.pizzame.databinding.ListRowBinding;
import com.demo.pizzame.model.PizzaPlace;
import com.demo.pizzame.viewModel.ListRowViewModel;

import java.util.List;

/**
 * Created by Surabhi on 7/29/17.
 */

public class PizzaPlaceAdapter extends RecyclerView.Adapter<PizzaPlaceAdapter.PizzaPlaceHolder> {

    private List<PizzaPlace> mPizzaPlaceData;
    private Context mContext;
    public PizzaPlaceAdapter(Context context, List<PizzaPlace> pizzaPlaceData) {
        this.mPizzaPlaceData = pizzaPlaceData;
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
        postBinding.setViewModel(new ListRowViewModel(mContext, mPizzaPlaceData.get(position)));
    }

    @Override
    public int getItemCount() {
        return mPizzaPlaceData == null ? 0 : mPizzaPlaceData.size();
    }

    public class PizzaPlaceHolder extends RecyclerView.ViewHolder {
        private ListRowBinding binding;

        public PizzaPlaceHolder(ListRowBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }
    }
}
