package com.edugonzlz.ristorapp.model;

import java.util.LinkedList;

/**
 * Created by Edu on 4/12/16.
 */

public class TableModel {

    private LinkedList<DishModel> mDishList;
    private Float mBill;
    private int mTableNumber;

    public TableModel(int tableNumber) {
        mTableNumber = tableNumber;
    }

    public LinkedList<DishModel> getDishList() {

        if (mDishList == null) {
            DishModel dish = new DishModel("Añade tus platos", null , 0f);
            mDishList = new LinkedList<>();
            mDishList.add(dish);
        }
        return mDishList;
    }

    public void setDishList(LinkedList<DishModel> dishList) {
        mDishList = dishList;
    }

    public Float getBill() {

        Float bill = 0f;
        for (DishModel dish:mDishList) {

            bill = bill + dish.price();
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
        return "Mesa " + getTableNumber();
    }
}
