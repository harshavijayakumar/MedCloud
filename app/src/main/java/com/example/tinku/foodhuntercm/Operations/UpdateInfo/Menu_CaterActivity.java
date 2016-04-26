package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tinku.foodhuntercm.R;

public class Menu_CaterActivity extends AppCompatActivity implements View.OnClickListener {
   String username;

    Button Aboutus, Updateinfo, Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cater);
        Intent intent = getIntent();
        username= intent.getStringExtra("username");

        Aboutus = (Button)findViewById(R.id.aboutus);

        Updateinfo = (Button) findViewById(R.id.updateinfo);
        Search = (Button) findViewById(R.id.customsearch);

    }

    public void init()
    {

    }


    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.aboutus:
                FragmentManager FM= getFragmentManager();
                FragmentTransaction FT= FM.beginTransaction();
                FragmentOne Fone=new FragmentOne();
                FT.add(R.id.customexps,Fone);
                FT.commit();
                break;
            case R.id.updateinfo:
                Intent intent = new Intent(getApplicationContext(), CaterUpload.class);
                intent.putExtra("username", username);
                startActivity(intent);
              /*  FragmentManager FM2= getFragmentManager();
                FragmentTransaction FT2= FM2.beginTransaction();
                FragmentTwo F2=new FragmentTwo();
                FT2.add(R.id.customexps,F2);
                FT2.commit();
                */
                break;
            case R.id.customsearch:
                FragmentManager FM3= getFragmentManager();
                FragmentTransaction FT3= FM3.beginTransaction();
                FragmentThree F3=new FragmentThree();
                FT3.add(R.id.customexps,F3);
                FT3.commit();
                break;

        }



    }

}
