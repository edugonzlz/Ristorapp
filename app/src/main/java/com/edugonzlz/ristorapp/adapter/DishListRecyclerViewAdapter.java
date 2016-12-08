package com.edugonzlz.ristorapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.edugonzlz.ristorapp.model.DishModel;

import java.util.LinkedList;

/**
 * Created by Edu on 8/12/16.
 */

public class DishListRecyclerViewAdapter extends RecyclerView.Adapter<DishListRecyclerViewAdapter.DishListViewHolder>{

    private  LinkedList<DishModel> mMainDishList;

    public DishListRecyclerViewAdapter(LinkedList<DishModel> mainDishList) {
        super();
        mMainDishList = mainDishList;
    }
    @Override
    public DishListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(DishListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMainDishList.size();
    }

    protected class DishListViewHolder extends RecyclerView.ViewHolder {
        public  DishListViewHolder(View itemView) {
            super(itemView);

        }
    }
}
