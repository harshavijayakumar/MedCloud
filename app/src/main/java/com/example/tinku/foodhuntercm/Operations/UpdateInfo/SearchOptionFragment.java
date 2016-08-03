package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

/* Import appropriate libraries */
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tinku.foodhuntercm.Exceptions.AppException;
import com.example.tinku.foodhuntercm.Operations.Search.SearchActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.adapter.BuildEntity;

import android.widget.*;

/* Fragment for showing search options on the screen */
public class SearchOptionFragment extends Fragment {
    Button search;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Inflate search options on the screeen */
        View v = inflater.inflate(R.layout.searchoptions, container, false);

        /* Get the appropriate resources here */

/*
        final RadioButton rba= (RadioButton)v.findViewById(R.id.american);

        final RadioButton rbi= (RadioButton)v.findViewById(R.id.indian);
        final RadioButton rbt= (RadioButton)v.findViewById(R.id.thai);
        final RadioButton rbc= (RadioButton)v.findViewById(R.id.chinese);
        */
        final EditText edx=(EditText)v.findViewById(R.id.editText6);
        final Spinner mySpinner=(Spinner) v.findViewById(R.id.spinner2);
        final Spinner mySpinner2=(Spinner) v.findViewById(R.id.spinner3);
        final Spinner mySpinner3=(Spinner) v.findViewById(R.id.spinner4);


        search = (Button)v.findViewById(R.id.searchbutton);

        /* Listen to click on search button */
        search.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v) {
                /* On click, set the food type and send it via intent */
                String foodtype = null;
             /*
                boolean american= rba.isChecked();
                boolean indian= rbi.isChecked();
                boolean thai= rbt.isChecked();
                boolean chinese = rbc.isChecked();
                */


                final String pillname = edx.getText().toString();


                if(mySpinner == null){
                    try {
                        throw  new AppException(1, "Missing spinner information");
                    } catch (AppException e) {
                        e.printStackTrace();
                    }
                }


                TextView textView = (TextView)mySpinner.getSelectedView();
                final String spinnerType1 = textView.getText().toString();



                if(mySpinner2 == null){
                    try {
                        throw  new AppException(1, "Missing spinner information");
                    } catch (AppException e) {
                        e.printStackTrace();
                    }
                }
                TextView textView2 = (TextView)mySpinner2.getSelectedView();
                final String spinnerType2 = textView2.getText().toString();



                if(mySpinner3 == null){
                    try {
                        throw  new AppException(1, "Missing spinner information");
                    } catch (AppException e) {
                        e.printStackTrace();
                    }
                }
                TextView textView3 = (TextView)mySpinner3.getSelectedView();
                final String spinnerType3 = textView3.getText().toString();


              /*  if (american) {
                    foodtype="American";
                }
                if (indian) {
                    foodtype="Indian";
                }
                if (thai) {
                    foodtype="Thai";
                }
                if (chinese) {
                    foodtype="Chinese";
                }

                Get the username from the bundle */
                Bundle bundle= getArguments();
                String username=bundle.getString("username");

                /* Start an intent to switch to search activity screen */
                /*
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("food_type",foodtype);
                startActivity(intent);
                */


                BuildEntity usr = new BuildEntity();
                usr.addreminder(username,pillname,spinnerType1,spinnerType2,spinnerType3,getContext());
            }
        });
        return v;
    }
}


