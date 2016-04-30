package com.example.tinku.foodhuntercm.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.Entities.User;
import com.example.tinku.foodhuntercm.Operations.JoinCommunity.RegisterActivity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_CaterActivity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_DinerActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.Requests.CaterUploadRequest;
import com.example.tinku.foodhuntercm.Requests.DinerUploadRequest;
import com.example.tinku.foodhuntercm.Requests.LoginRequest;
import com.example.tinku.foodhuntercm.Requests.RegisterRequest;
import com.example.tinku.foodhuntercm.Requests.SearchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ProxyLayer {
    private static User usrObj;
    private static User foodObj;
    private static LinkedHashMap<String, User> userHash = new LinkedHashMap<String, User>();
    private static LinkedHashMap<String, User> foodHash = new LinkedHashMap<String, User>();
    private boolean reqSts;
    private boolean result;

    public boolean getReqSts(){return reqSts;}

    public User getUserObj(String name){
        return userHash.get(name);
    }

	public void registerUser(String username, String password, String email, String type, Context registerContext){

        usrObj = new User();
		usrObj.setUserRegisterInfo(username, password, email, type);
        final Context lclContext = registerContext;
        final String usrname = username;
        final String usrtype = type;
        Response.Listener<String> responseListener = new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    result = jsonResponse.getBoolean("success");
                    if(result){
                        if(usrtype.equals("Diner")) {
                            Intent intent = new Intent(lclContext, Menu_DinerActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("username", usrname);
                            lclContext.startActivity(intent);
                        }

                        if(usrtype.equals("Cater")) {
                            Intent intent = new Intent(lclContext, Menu_CaterActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("username", usrname);
                            lclContext.startActivity(intent);
                        }


                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(lclContext);
                        builder.setMessage("Register Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(usrObj, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclContext);
        queue.add(registerRequest);

	}

    public void loginUser(String username, String password,Context loginContext){
        final Context lclCtxt = loginContext;
        final String usrname = username;
        final String passwd = password;
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String type = jsonResponse.getString("user");
                    if (success) {
                        if (type.equals("Diner")) {
                            Intent intent = new Intent(lclCtxt, Menu_DinerActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("username", usrname);
                            lclCtxt.startActivity(intent);
                        } else if (type.equals("Cater")) {
                            Intent intent = new Intent(lclCtxt, Menu_CaterActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("username", usrname);
                            lclCtxt.startActivity(intent);
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(lclCtxt);
                        builder.setMessage("Login Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginRequest loginRequest = new LoginRequest(usrname, passwd, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclCtxt);
        queue.add(loginRequest);
    }

    public void updateDinerInfo(String username, String userloc, int usercontact, Context DinerUploadCtxt){
        final String usrname = username;
        final String usrlocation = userloc;
        final int usrcontact = usercontact;
        final Context lclContext = DinerUploadCtxt;
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        //   String name = jsonResponse.getString("name");
                        Intent intent = new Intent(lclContext, Menu_DinerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("username", usrname);
                        lclContext.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(lclContext);
                        builder.setMessage("Diner Upload Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        DinerUploadRequest dinerUploadRequest = new DinerUploadRequest(username, usrlocation, usrcontact, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclContext);
        queue.add(dinerUploadRequest);
    }

    public void updateCaterInfo(String username, String foodType, String userloc, String foodDesc, float price, int usercontact, boolean imageset, Context caterCtxt){
        final String fusername = username;
        final String ffoodType = foodType;
        final String fuserloc = userloc;
        final String ffoodDesc = foodDesc;
        final float fprice = price;
        final int fusercontact = usercontact;
        final boolean fimgeset = imageset;
        final Context lclCtxt = caterCtxt;

        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        //   String name = jsonResponse.getString("name");
                        if(fimgeset == false) {

                            Intent intent = new Intent(lclCtxt, Menu_CaterActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("username", fusername);
                            lclCtxt.startActivity(intent);
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(lclCtxt);
                        builder.setMessage("Cater Upload Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        };


        CaterUploadRequest caterUploadRequest = new CaterUploadRequest(username, ffoodType,  fuserloc, ffoodDesc, fprice, fusercontact, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclCtxt);
        queue.add(caterUploadRequest);
    }


}