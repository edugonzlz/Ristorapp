package com.edugonzlz.ristorapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.fragment.MainDishListFragment;
import com.edugonzlz.ristorapp.model.DishModel;

import java.util.LinkedList;

/**
 * Created by Edu on 8/12/16.
 */

public class DishListRecyclerViewAdapter extends RecyclerView.Adapter<DishListRecyclerViewAdapter.DishListViewHolder>{

    private  LinkedList<DishModel> mMainDishList;

    public DishListRecyclerViewAdapter(LinkedList<DishModel> mainDishList, Activity activity, MainDishListFragment mainDishListFragment) {
        super();
        mMainDishList = mainDishList;
    }
    @Override
    public DishListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_dish, parent, false);

        return new DishListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishListViewHolder holder, int position) {
        holder.bindDish(mMainDishList.get(position));

    }

    @Override
    public int getItemCount() {
        return mMainDishList.size();
    }

    protected class DishListViewHolder extends RecyclerView.ViewHolder {

        private TextView mDishName;
        private TextView mDishPrice;
        private TextView mDishAllegerns;
        private ImageView mDishImage;
        private View mView;

        public  DishListViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            mDishName = (TextView) itemView.findViewById(R.id.dish_name);
            mDishPrice = (TextView) itemView.findViewById(R.id.dish_price);
            mDishAllegerns = (TextView) itemView.findViewById(R.id.dish_allergens);
            mDishImage = (ImageView) itemView.findViewById(R.id.dish_image);
        }

        public void bindDish(DishModel dishModel) {
            mDishName.setText(dishModel.getName());
            mDishPrice.setText(String.format(String.valueOf(dishModel.getPrice())));
            mDishAllegerns.setText((CharSequence) dishModel.getAllergens());
            mDishImage.setImageResource(dishModel.getPhoto());
        }
    }

    public interface OnDishClickListener {
        public void onDishClick(int position, DishModel dish, View view);
    }
}
