package com.example.tinku.foodhuntercm.adapter;

import android.content.Context;

import com.example.tinku.foodhuntercm.Entities.User;

/* CreateUser interface for building and printing User object */
public interface CreateEntity {
    public void registerUser(String username, String password, String email, String type, Context registerContext);
    public void loginUser(String username, String password,Context loginContext);
}
