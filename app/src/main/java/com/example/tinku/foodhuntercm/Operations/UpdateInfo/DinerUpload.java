package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.AlertDialog;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.Operations.Search.SearchActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.Requests.DinerUploadRequest;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;


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
                BuildEntity usr = new BuildEntity();
                usr.updateDinerInfo(username, dinerLocation, dinerContact, getApplicationContext());
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
