package com.example.tinku.foodhuntercm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DinerUpload extends AppCompatActivity {


    Button B1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diner_upload);


        B1=(Button)findViewById(R.id.updateinfo);

        B1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });
    }
}
