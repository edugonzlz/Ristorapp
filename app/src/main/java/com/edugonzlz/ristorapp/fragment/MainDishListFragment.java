package com.edugonzlz.ristorapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.adapter.DishListRecyclerViewAdapter;
import com.edugonzlz.ristorapp.model.DishListModel;
import com.edugonzlz.ristorapp.model.DishModel;
import com.edugonzlz.ristorapp.model.RestaurantModel;

import java.util.LinkedList;

/**
 * Created by Edu on 8/12/16.
 */

public class MainDishListFragment extends Fragment implements DishListRecyclerViewAdapter.OnDishClickListener{

    private RecyclerView mList;

    private LinkedList<DishModel> mDishList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtenemos la carta
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_main_dish_list, container, false);

        mList = (RecyclerView) root.findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //        mList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(new DishListRecyclerViewAdapter(new LinkedList<DishModel>(), getActivity(), this));

        return root;

    }

    @Override
    public void onDishClick(int position, DishModel dish, View view) {

        // Lo que hacemos cuando se pulsa un plato
    }
}
