package com.example.tinku.foodhuntercm.Entities;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by sandeep on 4/28/2016.
 */
public class Food {
    String fname;
    String fdescription;
    float fprice;
    Bitmap image;

    /* Constructor to initialize the important information */
    public Food() {
        fname = "";
        fdescription = "";
        fprice = 0;
        image = null;
    }

    /* Getter and Setter Methods */
    protected String getFoodName() {
        return fname;
    }

    protected String getFoodDescription() {
        return fdescription;
    }

    protected float getFoodPrice() {
        return fprice;
    }

    protected void setFoodInfo(String foodName, String foodDesc, float foodPrice, Bitmap foodImage){
        fname = foodName;
        fdescription = foodDesc;
        fprice = foodPrice;
        image = foodImage;
    }
}
