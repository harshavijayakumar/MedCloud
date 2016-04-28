package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;



import com.example.tinku.foodhuntercm.R;

public class Menu_DinerActivity extends AppCompatActivity implements View.OnClickListener {

    Button Aboutus,Updateinfo, Search;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_diner);
        Intent intent = getIntent();
         username = intent.getStringExtra("username");
       // iv=(ImageView)findViewById(R.id.imageView);
       // iv.setImageResource(R.drawable.pic);
        Log.d(" i am in Fragoneone", "onClick: ");
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
                FT.add(R.id.customexps2,Fone);
                FT.commit();
                break;
            case R.id.updateinfo:
                Intent intent = new Intent(getApplicationContext(), DinerUpload.class);
                intent.putExtra("username", username);
                startActivity(intent);
               /* FragmentManager FM2= getFragmentManager();
                FragmentTransaction FT2= FM2.beginTransaction();
                FragmentTwo F2=new FragmentTwo();
                FT2.add(R.id.customexps,F2);
                FT2.commit();
                */
                break;
            case R.id.customsearch:
                FragmentManager FM3= getFragmentManager();
                Bundle bundle= new Bundle();
                bundle.putString("username",username);
                FragmentTransaction FT3= FM3.beginTransaction();
                FragmentThree F3=new FragmentThree();
                F3.setArguments(bundle);
                FT3.add(R.id.customexps,F3);
                FT3.commit();
                break;

        }



    }


}
