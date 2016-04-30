package com.example.tinku.foodhuntercm.adapter;

import android.content.Context;

/* Update user interface */
public interface UpdateEntity {
    public void updateDinerInfo(String username, String userloc, int usercontact,Context dinerCtxt);
    public void updateCaterInfo(String username,String foodType,
                                String userloc, String foodDesc, float price, int usercontact,boolean imageset, Context caterCtxt);
}
