package com.edugonzlz.ristorapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.edugonzlz.ristorapp.R;
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
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_dish_list, container, false);

        mListView = (ListView) root.findViewById(android.R.id.list);

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
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_table,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.bill) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(R.string.bill);

            String bill = String.valueOf(RestaurantModel.sharedRestaurant().getTable(mTableIndex).getBill());
            alertDialog.setMessage(getString(R.string.toPay) + bill + getString(R.string.euro));
            alertDialog.setPositiveButton(R.string.Pay, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.setNegativeButton(R.string.wahDishes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alertDialog.show();
        }
        return superValue;
    }

    public interface OnDishSelectedListener {
        void onDishSelected(DishModel dish, int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick();
    }

}
