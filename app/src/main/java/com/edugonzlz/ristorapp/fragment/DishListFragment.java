package com.edugonzlz.ristorapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.model.DishListModel;
import com.edugonzlz.ristorapp.model.DishModel;
import com.edugonzlz.ristorapp.model.RestaurantModel;

import java.util.LinkedList;

/**
 * Created by Edu on 6/12/16.
 */

public class DishListFragment extends Fragment {

    private OnDishSelectedListener mOnDishSelectedListener;
    private OnButtonClickListener mOnButtonClickListener;

    private static final String ARG_TABLE_INDEX = "ARG_TABLE_INDEX";

    private int mTableIndex;
    private LinkedList<DishModel> mDishList;
    private RestaurantModel mRestaurant;
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish_list, container, false);

        mListView = (ListView) root.findViewById(android.R.id.list);

        //Creamos el modelo, que es la Dishlist de la mesa que nos pasen
//        mRestaurant = new RestaurantModel();
//        mRestaurant = RestaurantModel.sharedRestaurant();
//        mDishList = mRestaurant.getTable(mTableIndex).getDishList();

//        ArrayAdapter<DishModel> adapter = new ArrayAdapter<DishModel>(
//                getActivity(),
//                android.R.layout.simple_list_item_1,
//                mDishList
//        );
//
//        list.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (mOnDishSelectedListener != null) {
                    mOnDishSelectedListener.onDishSelected(mDishList.get(position), position);
                }
            }
        });

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mOnButtonClickListener != null) {
                    mOnButtonClickListener.onButtonClick();
                }
            }
        });

        return root;
    }

    public static DishListFragment newInstance(int tableIndex) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_TABLE_INDEX, tableIndex);

        DishListFragment dishListFragment = new DishListFragment();
        dishListFragment.setArguments(arguments);

        return dishListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() !=null) {
            mTableIndex = getArguments().getInt(ARG_TABLE_INDEX, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        LinkedList dishList = RestaurantModel
                .sharedRestaurant()
                .getTable(mTableIndex)
                .getDishList();

        ArrayAdapter<DishModel> adapter = new ArrayAdapter<DishModel>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                dishList
        );

        mListView.setAdapter(adapter);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof OnDishSelectedListener) {
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
        if (getActivity() instanceof OnButtonClickListener) {
            mOnButtonClickListener = (OnButtonClickListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof OnDishSelectedListener) {
            mOnDishSelectedListener = (OnDishSelectedListener) getActivity();
        }
        if (getActivity() instanceof OnButtonClickListener) {
            mOnButtonClickListener = (OnButtonClickListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnDishSelectedListener = null;
        mOnButtonClickListener = null;
    }


    public interface OnDishSelectedListener {
        void onDishSelected(DishModel dish, int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

}
