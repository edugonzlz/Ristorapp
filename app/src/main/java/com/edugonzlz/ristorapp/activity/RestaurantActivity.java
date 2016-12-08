package com.edugonzlz.ristorapp.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.fragment.TableListFragment;
import com.edugonzlz.ristorapp.model.TableModel;

public class RestaurantActivity extends AppCompatActivity implements TableListFragment.OnTableSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.fragment_table_list) != null) {
            if (fm.findFragmentById(R.id.fragment_table_list) == null) {
                fm.beginTransaction()
                        .add(R.id.fragment_table_list, new TableListFragment())
                        .commit();
            }
        }

    }

    public void onTableSelected(TableModel table, int position) {

        // Cuando pulsamos una mesa presentamos la actividad que corresponda
    }
}
