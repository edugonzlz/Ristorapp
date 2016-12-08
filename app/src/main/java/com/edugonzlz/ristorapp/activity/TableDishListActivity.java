package com.edugonzlz.ristorapp.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.fragment.DishListFragment;
import com.edugonzlz.ristorapp.fragment.TableListFragment;
import com.edugonzlz.ristorapp.model.DishModel;

/**
 * Created by Edu on 8/12/16.
 */

public class TableDishListActivity extends AppCompatActivity implements DishListFragment.OnButtonClickListener{

    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_table_dish_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getFragmentManager();

        if (findViewById(R.id.simple_list) != null) {
            if (fm.findFragmentById(R.id.simple_list) == null) {
                fm.beginTransaction()
                        .add(R.id.simple_list, new DishListFragment())
                        .commit();
            }
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

    @Override
    public void onButtonClick() {

        Intent intent = new Intent(this, MainDishListActivity.class);

        startActivity(intent);
    }
}
