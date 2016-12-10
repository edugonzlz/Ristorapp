package com.edugonzlz.ristorapp.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Edu on 4/12/16.
 */

public class DishModel implements Serializable{

    private String mName;
    private String mDescription;
    private String mAllergens;
    private String mNotes;
    private Float mPrice;
    private int mPhoto;

    public DishModel(String name, String description, Float price, int photo, String allergens) {
        mName = name;
        mDescription = description;
        mPrice = price;
        mPhoto = photo;
        mAllergens = allergens;
    }

    public DishModel(String name, String description, Float price) {
        mName = name;
        mDescription = description;
        mPrice = price;

    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getAllergens() {
        return mAllergens;
    }

    public void setAllergens(String allergens) {
        mAllergens = allergens;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getPrice() {
        return (String.valueOf(mPrice) + "€");
    }

    public Float price() { return mPrice; }

    public void setPrice(Float price) {
        mPrice = price;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        mPhoto = photo;
    }

    @Override
    public String toString() {
        return mName + " " + mPrice;
    }
}
