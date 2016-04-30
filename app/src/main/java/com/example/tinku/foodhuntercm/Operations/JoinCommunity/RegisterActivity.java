package com.example.tinku.foodhuntercm.Operations.JoinCommunity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.CaterUpload;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.DinerUpload;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_CaterActivity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_DinerActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.Requests.*;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;


import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    ImageView iv;
    Button regbutton;
    boolean result;

    EditText etName,etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        iv=(ImageView)findViewById(R.id.imageview);
        iv.setImageResource(R.drawable.logo);
        etName=(EditText)findViewById(R.id.reg_fullname);
        etEmail=(EditText)findViewById(R.id.reg_email);
        etPassword=(EditText)findViewById(R.id.reg_password);

        regbutton=(Button) findViewById(R.id.registerbutton);
        regbutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final String username = etName.getText().toString();
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                Spinner mySpinner=(Spinner) findViewById(R.id.spinner2);
                TextView textView = (TextView)mySpinner.getSelectedView();

                final String spinnerType = textView.getText().toString();
                BuildEntity usr = new BuildEntity();
                usr.registerUser(username, password, email,spinnerType,getApplicationContext());
            }
        });
    }
}
