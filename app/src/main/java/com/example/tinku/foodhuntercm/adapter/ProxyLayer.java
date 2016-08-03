package com.example.tinku.foodhuntercm.adapter;

/* Import appropriate libraries */
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.Entities.User;
import com.example.tinku.foodhuntercm.Operations.Search.SearchActivity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_CaterActivity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_DinerActivity;
import com.example.tinku.foodhuntercm.Requests.AddReminder;
import com.example.tinku.foodhuntercm.Requests.CaterUploadRequest;
import com.example.tinku.foodhuntercm.Requests.DinerUploadRequest;
import com.example.tinku.foodhuntercm.Requests.LoginRequest;
import com.example.tinku.foodhuntercm.Requests.RegisterRequest;
import org.json.JSONException;
import org.json.JSONObject;

/* Abstract class implementing methods from BuildEntity */
public abstract class ProxyLayer {

    /* Variable for handling different operations of the application */
    private boolean result;


    /* Register user operation */
    public void addreminder( String username, String pillname,String password, String email, String type, Context registerContext){
        User usrObj;
        usrObj = new User();
        usrObj.setUserRegisterInfo(username,pillname, password, email, type);
        final Context lclContext = registerContext;
        final String usrname = username;
        final String usrtype = type;

        /* Send the request to the server */
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                /* Up on response, jump to menu page based on the user(cater/diner) */
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    result = jsonResponse.getBoolean("success");
                    if(result){
/*
                        Intent intent = new Intent(lclContext, SearchActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("username", usrname);
                        lclContext.startActivity(intent);
*/
                        /* if(usrtype.equals("Diner")) {
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
                        */
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

        /* Add the HTTP request to queue and send to server */
        AddReminder registerRequest = new AddReminder(usrObj, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclContext);
        queue.add(registerRequest);
    }



    /* Register user operation */
	public void registerUser(String username, String password, String email, String type, Context registerContext){
        User usrObj;
        usrObj = new User();
		usrObj.setUserRegisterInfo2(username, password, email, type);
        final Context lclContext = registerContext;
        final String usrname = username;
        final String usrtype = type;

        /* Send the request to the server */
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                /* Up on response, jump to menu page based on the user(cater/diner) */
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    result = jsonResponse.getBoolean("success");
                    if(result){

                            Intent intent = new Intent(lclContext, Menu_DinerActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("username", usrname);
                            lclContext.startActivity(intent);

                        /* if(usrtype.equals("Diner")) {
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
                        */
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

        /* Add the HTTP request to queue and send to server */
        RegisterRequest registerRequest = new RegisterRequest(usrObj, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclContext);
        queue.add(registerRequest);
	}

    /* Login user operation */
    public void loginUser(String username, String password,Context loginContext){
        final Context lclCtxt = loginContext;
        final String usrname = username;

        /* Send the request to the server */
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /* Up on response, jump to menu page based on the user(cater/diner) */
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    String type = jsonResponse.getString("user");
                    if (success) {
                       /* if (type.equals("Diner")) {
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
                        */


                        Intent intent = new Intent(lclCtxt, Menu_DinerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("username", usrname);
                        lclCtxt.startActivity(intent);

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

        /* Add the HTTP request to queue and send to server */
        LoginRequest loginRequest = new LoginRequest(usrname, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclCtxt);
        queue.add(loginRequest);
    }

    /* Update diner information operation */
    public void updateDinerInfo(String username, String userloc, int usercontact, Context DinerUploadCtxt){
        final String usrname = username;
        final Context lclContext = DinerUploadCtxt;
        /* Send the request to the server */
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 /* Up on response, jump to menu page based on the diner */
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
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

        /* Add the HTTP request to queue and send to server */
        DinerUploadRequest dinerUploadRequest = new DinerUploadRequest(username, userloc, usercontact, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclContext);
        queue.add(dinerUploadRequest);
    }

    /* Update cater information operation */
    public void updateCaterInfo(String username, String foodType, String userloc, String foodDesc, float price, int usercontact, boolean imageset, Context caterCtxt){
        final String fusername = username;
        final boolean fimgeset = imageset;
        final Context lclCtxt = caterCtxt;

        /* Send the request to the server */
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /* Up on response, jump to menu page based on the cater */
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        if(!fimgeset) {
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

        /* Add the HTTP request to queue and send to server */
        CaterUploadRequest caterUploadRequest = new CaterUploadRequest(username, foodType,  userloc, foodDesc, price, usercontact, responseListener);
        RequestQueue queue = Volley.newRequestQueue(lclCtxt);
        queue.add(caterUploadRequest);
    }
}