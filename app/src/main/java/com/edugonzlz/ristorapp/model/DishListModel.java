package com.edugonzlz.ristorapp.model;

import java.util.LinkedList;

/**
 * Created by Edu on 4/12/16.
 */

public class DishListModel {

    private LinkedList<DishModel> mDishList;

    public DishListModel(LinkedList<DishModel> dishList) {
        mDishList = dishList;
    }

    public DishListModel() {
        mDishList = new LinkedList<>();

    }

    public LinkedList<DishModel> getDishList() {
        return mDishList;
    }

    public void setDishList(LinkedList<DishModel> dishList) {
        mDishList = dishList;
    }

    public DishModel getDish(int position) {return  mDishList.get(position); }

    public int getNumberOfDishes() {
        return mDishList.size();
    }

    public void addDish (DishModel dish) {

        mDishList.add(dish);
    }
}
