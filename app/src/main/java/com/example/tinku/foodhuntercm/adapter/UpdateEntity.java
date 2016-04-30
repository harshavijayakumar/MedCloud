package com.example.tinku.foodhuntercm.adapter;

/* Import appropriate libraries */
import android.content.Context;

/* Interface for update of cater and diner information */
public interface UpdateEntity {
    public void updateDinerInfo(String username, String userloc, int usercontact,Context dinerCtxt);
    public void updateCaterInfo(String username,String foodType,
                                String userloc, String foodDesc, float price, int usercontact,boolean imageset, Context caterCtxt);
}
