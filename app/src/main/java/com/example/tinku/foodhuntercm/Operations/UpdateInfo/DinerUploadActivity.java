package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

/* Import appropriate libraries */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tinku.foodhuntercm.Exceptions.AppException;
import com.example.tinku.foodhuntercm.Operations.JoinCommunity.LoginActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;

/* Diner upload activity screen for the application */
public class DinerUploadActivity extends AppCompatActivity {

    /* Variables for handling diner upload activity */
    Button btDinerUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Create layout of Diner upload screen */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diner_upload);
        setTitle(R.string.app_name);

        /* Get the ids of the resources */
        final EditText location;
        final EditText contact;
        location = (EditText) findViewById(R.id.etLocation);
        contact = (EditText) findViewById(R.id.etContactNumber);
        btDinerUpdate = (Button) findViewById(R.id.updateinfo);

        /* Get the username information from the calling activity */
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        /* Listen to button click on diner update */
        btDinerUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               /* Get the diner information */
                final String dinerLocation = location.getText().toString();
                final int dinerContact = Integer.parseInt(contact.getText().toString());

                /*
                 * Build entity is an abstraction created from application
                 * perspective which hides the details about implementation
                 * of requests. This way user has to just has to call the
                 * APIs provided to them with proper parameters.
                 */
                BuildEntity usr = new BuildEntity();
                usr.updateDinerInfo(username, dinerLocation, dinerContact, getApplicationContext());
            }
        });


        try {
            Button btnLogout  = (Button)findViewById(R.id.logout);
            if(btnLogout == null){
                throw  new AppException(1, "Missing information");
            }

            btnLogout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                /* On click of register, start an Intent to switch to register user screen */
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
            });
        }
        catch(AppException e){
            e.genericexceptionfix();
        }
    }

    /* On Start and On stop methods */
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
