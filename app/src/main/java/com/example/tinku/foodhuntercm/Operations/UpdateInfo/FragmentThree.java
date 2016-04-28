package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tinku.foodhuntercm.Operations.Search.SearchActivity;
import com.example.tinku.foodhuntercm.R;



import android.app.Fragment;
import com.example.tinku.foodhuntercm.R;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.app.Fragment;
import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.io.*;

import android.support.v7.widget.AppCompatButton;

import java.io.FilenameFilter;
import java.util.*;
import java.util.ArrayList;
import android.app.ListFragment;



/**
 * Created by tinku on 4/2/16.
 */


public class FragmentThree extends Fragment {


 Button search;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.searchoptions, container, false);



       // final RadioGroup rg= (RadioGroup) v.findViewById(R.id.radiogroup);
        final RadioButton rba= (RadioButton)v.findViewById(R.id.american);
        search = (Button)v.findViewById(R.id.searchbutton);
        final RadioButton rbi= (RadioButton)v.findViewById(R.id.indian);
        final RadioButton rbt= (RadioButton)v.findViewById(R.id.thai);
        final RadioButton rbc= (RadioButton)v.findViewById(R.id.chinese);


        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


             String foodtype = null;
               // int selectedId = rg.getCheckedRadioButtonId();
                boolean american= rba.isChecked();
                boolean indian= rbi.isChecked();
                boolean thai= rbt.isChecked();
                boolean chiner= rbc.isChecked();

                if (american) {
                    foodtype="American";

                }

                if (indian) {
                    foodtype="Indian";

                }
                if (thai) {
                    foodtype="Thai";
                }

                if (chiner) {
                    foodtype="Chinese";
                }

              //  Intent intent = new Intent(getActivity(),SearchActivity.class);
                // intent.putExtra("name", name);
                // intent.putExtra("username", username);
                Bundle bundle= getArguments();
                String username=bundle.getString("username");
                Log.d(username, "username: ");


                 Intent intent = new Intent(getActivity(),SearchActivity.class);
               intent.putExtra("username", username);
                intent.putExtra("food_type",foodtype);
                startActivity(intent);
               // FragmentThree.this.startActivity(intent);

            }
        });



        return v;
    }

}


