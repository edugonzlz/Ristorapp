package com.edugonzlz.ristorapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.model.DishListModel;
import com.edugonzlz.ristorapp.model.DishModel;

/**
 * Created by Edu on 6/12/16.
 */

public class DishListFragment extends Fragment {

    private OnDishSelectedListener mOnDishSelectedListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish_list, container, false);

        ListView list = (ListView) root.findViewById(android.R.id.list);

        final DishListModel dishList = new DishListModel();

        ArrayAdapter<DishModel> adapter = new ArrayAdapter<DishModel>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dishList.getDishList()
        );

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (mOnDishSelectedListener != null) {
                    mOnDishSelectedListener.onDishSelected(dishList.getDish(position), position);
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnDishSelectedListener) {
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof OnDishSelectedListener) {
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnDishSelectedListener = null;
    }

    public interface OnDishSelectedListener {
        void onDishSelected(DishModel dish, int position);
    }
}
