package com.example.tinku.foodhuntercm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.AlertDialog;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.Requests.CaterUploadRequest;
import com.example.tinku.foodhuntercm.Requests.DinerUploadRequest;


import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by sandeep on 4/12/2016.
 */
public class CaterUpload extends AppCompatActivity {
    ImageView iv;
    Button btnCaterUpdate;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.logo);

        final EditText name,location, foodName, price, contactNumber;
        name = (EditText) findViewById(R.id.etCaterName);
        location = (EditText) findViewById(R.id.etLocation);
        foodName = (EditText) findViewById(R.id.etFood);
        price = (EditText)findViewById(R.id.etPrice);
        contactNumber = (EditText)findViewById(R.id.etContactNumber);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");


        btnCaterUpdate = (Button)findViewById(R.id.updatebutton);
        btnCaterUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                final String caterName = name.getText().toString();
                final String caterLocation = location.getText().toString();
                final String caterFood = foodName.getText().toString();
                final float foodPrice = Float.parseFloat(price.getText().toString());
                final int caterContact = Integer.parseInt(contactNumber.getText().toString());
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                //   String name = jsonResponse.getString("name");
                                Intent intent = new Intent(CaterUpload.this, SearchActivity.class);
                                // intent.putExtra("name", name);
                                // intent.putExtra("username", username);

                                //  intent.putExtra("age", age);
                                CaterUpload.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(CaterUpload.this);
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
                CaterUploadRequest caterUploadRequest = new CaterUploadRequest(username, caterName, caterLocation, caterFood, foodPrice, caterContact, responseListener);
                RequestQueue queue = Volley.newRequestQueue(CaterUpload.this);
                queue.add(caterUploadRequest);

            }
        });

    }
}


