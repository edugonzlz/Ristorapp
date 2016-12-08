package com.edugonzlz.ristorapp.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.edugonzlz.ristorapp.R;

/**
 * Created by Edu on 8/12/16.
 */

public class MainDishListActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_dish_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getFragmentManager();

//        if (fm.findFragmentById(R.id.fragment_city_pager) == null) {
//            // Le paso la ciudad que quiere el usuario cargar
//            int cityIndex = getIntent().getIntExtra(EXTRA_CITY_INDEX, 0);
//
//            // Creo el fragment pasándole los argumentos
//            CityPagerFragment cityPagerFragment = CityPagerFragment.newInstance(cityIndex);
//
//            fm.beginTransaction()
//                    .add(R.id.fragment_city_pager, cityPagerFragment)
//                    .commit();
//        }


    }
}
