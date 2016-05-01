package com.example.tinku.foodhuntercm.Operations.UpdateInfo;

/* Import appropriate libraries */
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.tinku.foodhuntercm.Operations.Search.SearchActivity;
import com.example.tinku.foodhuntercm.R;
import android.widget.*;

/* Fragment for showing search options on the screen */
public class SearchOptionFragment extends Fragment {
    Button search;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Inflate search options on the screeen */
        View v = inflater.inflate(R.layout.searchoptions, container, false);

        /* Get the appropriate resources here */
        final RadioButton rba= (RadioButton)v.findViewById(R.id.american);
        search = (Button)v.findViewById(R.id.searchbutton);
        final RadioButton rbi= (RadioButton)v.findViewById(R.id.indian);
        final RadioButton rbt= (RadioButton)v.findViewById(R.id.thai);
        final RadioButton rbc= (RadioButton)v.findViewById(R.id.chinese);

        /* Listen to click on search button */
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* On click, set the food type and send it via intent */
                String foodtype = null;
                boolean american= rba.isChecked();
                boolean indian= rbi.isChecked();
                boolean thai= rbt.isChecked();
                boolean chinese = rbc.isChecked();

                if (american) {
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

                /* Get the username from the bundle */
                Bundle bundle= getArguments();
                String username=bundle.getString("username");

                /* Start an intent to switch to search activity screen */
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("food_type",foodtype);
                startActivity(intent);
            }
        });
        return v;
    }
}


