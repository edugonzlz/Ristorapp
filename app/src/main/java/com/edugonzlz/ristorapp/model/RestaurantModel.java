package com.edugonzlz.ristorapp.model;

import java.util.LinkedList;

/**
 * Created by Edu on 4/12/16.
 */

public class RestaurantModel {

    private LinkedList<TableModel> mTableList;
    private DishListModel mDishlist;

    public RestaurantModel(DishListModel dishlist) {
        mDishlist = dishlist;

        mTableList = new LinkedList<>();
        mTableList.add(new TableModel(1));
        mTableList.add(new TableModel(2));
        mTableList.add(new TableModel(3));
    }

    public DishListModel getDishlist() {
        return mDishlist;
    }

    public void setDishlist(DishListModel dishlist) {
        mDishlist = dishlist;
    }

    public LinkedList<TableModel> getTableList() {
        return mTableList;
    }

    public void setTableList(LinkedList<TableModel> tableList) {
        mTableList = tableList;
    }

    public int getNumberOfTables() {
        return mTableList.size();
    }

    public TableModel getTable(int position) {
        return mTableList.get(position);
    }
}
