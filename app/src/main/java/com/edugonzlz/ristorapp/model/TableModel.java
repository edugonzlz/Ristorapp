package com.edugonzlz.ristorapp.model;

import java.util.LinkedList;

/**
 * Created by Edu on 4/12/16.
 */

public class TableModel {

    private DishListModel mDishList;
    private Float mBill;
    private int mTableNumber;

    public TableModel(int tableNumber) {
        mTableNumber = tableNumber;
    }

    public DishListModel getDishList() {
        return mDishList;
    }

    public void setDishList(DishListModel dishList) {
        mDishList = dishList;
    }

    public Float getBill() {

        Float bill = 0f;
        for (DishModel dish:mDishList.getDishList()) {

            bill = bill + dish.getPrice();

        }
        return bill;
    }

    public void setBill(Float bill) {
        mBill = bill;
    }

    public int getTableNumber() { return mTableNumber; }

    public void setTableNumber(int tableNumber) {
        mTableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return "Table " + getTableNumber();
    }
}
