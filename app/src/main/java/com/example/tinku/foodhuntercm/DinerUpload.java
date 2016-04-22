package com.example.tinku.foodhuntercm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.AlertDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.Requests.DinerUploadRequest;


import org.json.JSONException;
import org.json.JSONObject;

public class DinerUpload extends AppCompatActivity {
    Button btDinerUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diner_upload);
        final EditText dname;
        final EditText location;
        final EditText contact;


        location = (EditText) findViewById(R.id.etLocation);
        contact = (EditText) findViewById(R.id.etContactNumber);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");


        btDinerUpdate = (Button) findViewById(R.id.updateinfo);
        btDinerUpdate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                /* Switching to Register screen */

                final String dinerLocation = location.getText().toString();
                final int dinerContact = Integer.parseInt(contact.getText().toString());
                final Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                //   String name = jsonResponse.getString("name");
                                Intent intent = new Intent(DinerUpload.this, SearchActivity.class);
                                // intent.putExtra("name", name);
                                // intent.putExtra("username", username);

                                //  intent.putExtra("age", age);
                                DinerUpload.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(DinerUpload.this);
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
                DinerUploadRequest dinerUploadRequest = new DinerUploadRequest(username, dinerLocation, dinerContact, responseListener);
                RequestQueue queue = Volley.newRequestQueue(DinerUpload.this);
                queue.add(dinerUploadRequest);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }
}
