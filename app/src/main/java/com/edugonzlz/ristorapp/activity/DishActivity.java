package com.edugonzlz.ristorapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.edugonzlz.ristorapp.R;
import com.edugonzlz.ristorapp.model.DishModel;
import com.edugonzlz.ristorapp.model.RestaurantModel;

/**
 * Created by Edu on 8/12/16.
 */

public class DishActivity extends AppCompatActivity{

    public static final String EXTRA_DISH = "EXTRA_DISH";
    public static final String EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX";

    private TextView mDishName;
    private TextView mDishPrice;
    private TextView mDishDescription;
    private TextView mDishAllegerns;
    private ImageView mDishImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mDishName = (TextView) findViewById(R.id.dish_name);
        mDishPrice = (TextView) findViewById(R.id.dish_price);
        mDishDescription = (TextView) findViewById(R.id.dish_description);
        mDishAllegerns = (TextView) findViewById(R.id.dish_allergens);
        mDishImage = (ImageView) findViewById(R.id.dish_image);

        final DishModel dish = (DishModel) getIntent().getSerializableExtra(EXTRA_DISH);
        getSupportActionBar().setTitle(dish.getName());

//        mDishName.setText(dish.getName());
        mDishPrice.setText(getString(R.string.Price) + dish.getPrice());
        mDishDescription.setText(getString(R.string.Description) + dish.getDescription());
        mDishAllegerns.setText(getString(R.string.Allergens) + (CharSequence) dish.getAllergens());
        mDishImage.setImageResource(dish.getPhoto());

        findViewById(R.id.addDishToTable).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int tableIndex = getIntent().getIntExtra(EXTRA_TABLE_INDEX, 0);
                RestaurantModel
                        .sharedRestaurant()
                        .getTable(tableIndex)
                        .getDishList()
                        .add(dish);

                Snackbar.make(view, "Añadido a la mesa " + String.valueOf(tableIndex +1), Snackbar.LENGTH_LONG).show();

            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
