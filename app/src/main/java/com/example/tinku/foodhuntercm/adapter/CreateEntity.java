package com.example.tinku.foodhuntercm.adapter;

/* Import appropriate libraries */
import android.content.Context;

/* CreateUser interface for register and login actions */
public interface CreateEntity {
    public void registerUser(String username, String password, String email, String type, Context registerContext);
    public void loginUser(String username, String password,Context loginContext);
}
