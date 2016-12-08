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

        DishModel dish = new DishModel("patatas", "con chorizo", 25f);
        DishModel dish2 = new DishModel("huevos", "con jamon", 15f);
        DishModel dish3 = new DishModel("arroz", "con leche", 10f);
        DishModel dish4 = new DishModel("pescado", "al horno", 10f);
        DishModel dish5 = new DishModel("carne", "a la piedra", 10f);
        DishModel dish6 = new DishModel("pan", "gallego", 10f);
        mDishList.add(dish);
        mDishList.add(dish2);
        mDishList.add(dish3);
        mDishList.add(dish4);
        mDishList.add(dish5);
        mDishList.add(dish6);

        // hacer la descarga aqui?
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
