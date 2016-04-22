package com.example.tinku.foodhuntercm;

import com.example.tinku.foodhuntercm.Requests.*;

import android.app.AlertDialog;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

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
                final Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String type = jsonResponse.getString("user");
                            if (success) {
                                if(type.equals("Diner")) {
                                    Intent intent = new Intent(getApplicationContext(), DinerUpload.class);
									intent.putExtra("username", username);
                                    startActivity(intent);               
                                }
                                else if(type.equals("Cater")){
                                    Intent intent = new Intent(getApplicationContext(), CaterUpload.class);
									intent.putExtra("username", username);
                                    startActivity(intent);
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            };




        });
    }
}







