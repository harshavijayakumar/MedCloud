package com.example.tinku.foodhuntercm;
import com.example.tinku.foodhuntercm.Requests.*;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    ImageView iv;
    Button regbutton;

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
                String type = textView.getText().toString();
               // final int age = Integer.parseInt(etAge.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){

                                Spinner mySpinner=(Spinner) findViewById(R.id.spinner2);
                                TextView textView = (TextView)mySpinner.getSelectedView();
                                String result = textView.getText().toString();

                                if(result.equals("Diner")) {

                                    Intent i = new Intent(getApplicationContext(), DinerUpload.class);
                                    startActivity(i);
                                }

                                if(result.equals("Cater")) {

                                    Intent i = new Intent(getApplicationContext(), CaterUpload.class);
                                    startActivity(i);
                                }

                              //  Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                               // RegisterActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
                RegisterRequest registerRequest = new RegisterRequest(email,type, username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

                // Switching to Register screen

            }





        });




    }
}
