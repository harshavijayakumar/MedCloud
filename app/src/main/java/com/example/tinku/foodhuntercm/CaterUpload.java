package com.example.tinku.foodhuntercm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by sandeep on 4/12/2016.
 */
public class CaterUpload extends AppCompatActivity {
    ImageView iv;
    Button B1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);



        iv = (ImageView) findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.logo);


B1=(Button)findViewById(R.id.updatebutton);
        B1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(i);
            }
        });

    }
}


