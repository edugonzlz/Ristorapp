package com.edugonzlz.ristorapp.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.fragment.MainDishListFragment;

/**
 * Created by Edu on 8/12/16.
 */

public class MainDishListActivity extends AppCompatActivity{

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_dish_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("CARTA DE COMIDAS");

        FragmentManager fm = getFragmentManager();
        String tableIndex = getIntent().getStringExtra(EXTRA_TABLE_INDEX);
        MainDishListFragment mainDishListFragment = MainDishListFragment.newInstance(tableIndex);

        if (fm.findFragmentById(R.id.fragment_main_dish_list) == null) {
            fm.beginTransaction()
                    .add(R.id.fragment_main_dish_list, mainDishListFragment)
                    .commit();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return superValue;
    }
}
