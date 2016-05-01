package com.example.tinku.foodhuntercm.Operations.Search;

/* Import appropriate libraries */
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.tinku.foodhuntercm.Operations.Payment.GoogleWalletNBActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.Requests.SearchRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

/* Search Activity screen for the application */
public class SearchActivity extends ListActivity {

    /* Variables for handling Search activity */
    private static final String STAR_STATES = "listviewtipsandtricks:star_states";
    private boolean[] mStarStates;
    private  List<String> list;
    Bundle xx;
    JSONArray jarry;
    private AccessoriesAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /* Create layout for search screen */
        super.onCreate(savedInstanceState);

        /* Get the bundled instance state */
        xx=savedInstanceState;

        /* Get the username, food type for starting search process */
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String food_type = intent.getStringExtra("food_type");

        /* List for populating search results */
        list = new ArrayList<>();

        /* Send the request to data base for getting the food item information */
        final Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                /* On response, populate the list */
                try {
                    //Log.d(response.toString(), "onResponse: ");
                     jarry = new JSONArray(response);


                     //  Log.d(response.toString(), "onResponseeee: ");
                     /* Iterate through the Json array for food items list */
                     for (int i=0;i< jarry.length();i++) {
                         JSONObject jobj = jarry.getJSONObject(i);

                         /* Store the information from json response here */
                         String price = jobj.getString("price");
                         String location = jobj.getString("location");
                         String food_type = jobj.getString("food_type");
                         String food_desc = jobj.getString("food_description");
                         String food_string = "$" + price + "\n" + location + "\n" + food_type + "\n" + food_desc;

                         /* Form a food string and add it in list */
                         list.add(food_string);
                         //Log.d(list.get(i).toString(), "list item: ");

                         /* Check the instance states here */
                         if (xx != null) {
                             mStarStates = xx.getBooleanArray(STAR_STATES);
                         } else {
                             mStarStates = new boolean[list.size()];
                         }
                     }
                } catch (JSONException e) {
                    /* Create an alert that search activity failed */
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                    builder.setMessage("Search Failed")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                    e.printStackTrace();
                }

                /* Set list adapter */
                mAdapter = new AccessoriesAdapter();
                setListAdapter(mAdapter);
            }


        };

        /* Form the search request and query the database */
        SearchRequest SearchRequest = new SearchRequest(username,food_type,responseListener);
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(SearchRequest);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /* Override the saved instance state */
        super.onSaveInstanceState(outState);
        outState.putBooleanArray(STAR_STATES, mStarStates);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        /* Nothing to do */
    }

    /* Form accesories view holder */
    private static class AccessoriesViewHolder {
        public CheckBox star;
        public TextView content;
    }

    /* Extend the base adapter */
    private class AccessoriesAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
           // return CHEESES[position];
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /* If convert view is null, get the holder information
             * set the tag
             * If convert view is already present get the tag information
             */
            AccessoriesViewHolder holder;
            if (convertView == null)
            {
                ListView framestub= (ListView)findViewById(R.id.list_view);
                convertView = getLayoutInflater().inflate(R.layout.searchstub,framestub ,false);
                holder = new AccessoriesViewHolder();
                holder.star = (CheckBox) convertView.findViewById(R.id.btn_star);
                holder.star.setOnCheckedChangeListener(mStarCheckedChanceChangeListener);
                holder.content = (TextView) convertView.findViewById(R.id.content);
                ( convertView.findViewById(R.id.btn_buy)).setOnClickListener(mBuyButtonClickListener);
                convertView.setTag(holder);
            } else {
                holder = (AccessoriesViewHolder)convertView.getTag();
            }

            /* Holder to update stars information */
            holder.star.setChecked(mStarStates[position]);
            holder.content.setText(list.get(position));
            return convertView;
        }
    }

    private void showMessage(String message) {
        Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    /* Listen to buy button */
    private OnClickListener mBuyButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            /* Onclick of Buy button, create an intent for google wallet */
            final int position = getListView().getPositionForView(v);
            //Log.d(String.valueOf(position), "onClick: ");
            //Log.d(list.get(position).toString(), "list item: ");
            String s=list.get(position);
            String price=s.substring(s.indexOf("$") + 1, s.indexOf("\n"));
            //Log.d(price, ":price");

            Intent intent = getIntent();
            final String username = intent.getStringExtra("username");
            Intent intent2 = new Intent(SearchActivity.this,GoogleWalletNBActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("price",price);
            SearchActivity.this.startActivity(intent2);
            if (position != ListView.INVALID_POSITION) {
                showMessage("Buy Format");
            }
        }
    };

    /* Method for handling stars changes on the screen */
    private OnCheckedChangeListener mStarCheckedChanceChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            /* Nothing to do */
        }
    };
}

