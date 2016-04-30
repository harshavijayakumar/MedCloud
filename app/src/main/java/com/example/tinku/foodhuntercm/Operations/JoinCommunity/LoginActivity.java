package com.example.tinku.foodhuntercm.Operations.JoinCommunity;

import com.example.tinku.foodhuntercm.Operations.UpdateInfo.CaterUpload;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.DinerUpload;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_CaterActivity;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_DinerActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.Requests.*;

import android.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    ImageView iv;
    Button btlogin;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=(ImageView)findViewById(R.id.imageview);
        iv.setImageResource(R.drawable.logo);


        btlogin= (Button)findViewById(R.id.btnLogin);
        etUsername=(EditText)findViewById(R.id.username);
        etPassword=(EditText)findViewById(R.id.pwd);

        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);

        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }



        });


        btlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                BuildEntity usr = new BuildEntity();
                usr.loginUser(username, password, getApplicationContext());
            };
        });
    }
}







