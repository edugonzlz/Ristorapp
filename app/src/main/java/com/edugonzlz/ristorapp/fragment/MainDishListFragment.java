package com.edugonzlz.ristorapp.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.activity.DishActivity;
import com.edugonzlz.ristorapp.adapter.DishListRecyclerViewAdapter;
import com.edugonzlz.ristorapp.model.DishListModel;
import com.edugonzlz.ristorapp.model.DishModel;

import java.util.LinkedList;

/**
 * Created by Edu on 8/12/16.
 */

public class MainDishListFragment extends Fragment implements DishListRecyclerViewAdapter.OnDishClickListener{

    private RecyclerView mList;

    private DishListModel mDishList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creamos una carta para crear un restaurante
        mDishList = new DishListModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_main_dish_list, container, false);

        mList = (RecyclerView) root.findViewById(R.id.recycler_view);
        mList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //        mList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mList.setItemAnimator(new DefaultItemAnimator());
        mList.setAdapter(new DishListRecyclerViewAdapter(mDishList.getDishList(), getActivity(), this));

        return root;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onDishClick(int position, DishModel dish, View view) {

        // Vamos a mostrar la vista de detalle
        Intent intent = new Intent(getActivity(), DishActivity.class);
        intent.putExtra(DishActivity.EXTRA_DISH, dish);

        startActivity(intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        view,
                        getString(R.string.transition_to_detail) // El nombre dentro de la vista destino
                ).toBundle());
    }

}
