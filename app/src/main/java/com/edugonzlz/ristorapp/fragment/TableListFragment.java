package com.edugonzlz.ristorapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
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
import com.edugonzlz.ristorapp.model.TableModel;

/**
 * Created by Edu on 6/12/16.
 */

public class TableListFragment extends Fragment{
    private TableListFragment.OnTableSelectedListener mOnTableSelectedListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_table_list, container, false);

        // Accedemos al ListView
        ListView list = (ListView) root.findViewById(R.id.table_list);

        // Descargamos los datos y obtenemos la DishList
        DishModel dish = new DishModel("patatas", "con chorizo", 25f);
        final DishListModel dishListModel = new DishListModel();
        dishListModel.addDish(dish);

        // Creamos nuestro modelo
        final RestaurantModel restaurant = new RestaurantModel(dishListModel);

        // Creamos un adaptador para poner en común el modelo con la lista
        ArrayAdapter<TableModel> adapter = new ArrayAdapter<TableModel>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                restaurant.getTableList()
        );

        // Le asignamos el adaptador a la lista
        list.setAdapter(adapter);

        // Le asigno un listener a la lista para enterarme de cuándo se ha pulsado una fila
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Avisamos al listener que el usuario ha pulsado una fila
                if (mOnTableSelectedListener != null) {
                    mOnTableSelectedListener.onTableSelected(restaurant.getTable(position), position);
                }
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof DishListFragment.OnDishSelectedListener) {
            mOnTableSelectedListener = (TableListFragment.OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (getActivity() instanceof DishListFragment.OnDishSelectedListener) {
            mOnTableSelectedListener = (TableListFragment.OnTableSelectedListener) getActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mOnTableSelectedListener = null;
    }

    public interface OnTableSelectedListener {
        void onTableSelected(TableModel table, int position);
    }
}