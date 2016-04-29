package com.example.tinku.foodhuntercm.Operations.Search;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.example.tinku.foodhuntercm.Operations.Payment.GoogleWalletNB;
import com.example.tinku.foodhuntercm.Operations.UpdateInfo.Menu_CaterActivity;
import com.example.tinku.foodhuntercm.R;
import com.example.tinku.foodhuntercm.Requests.SearchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class SearchActivity extends ListActivity {

    private static final String STAR_STATES = "listviewtipsandtricks:star_states";
    private boolean[] mStarStates;
    private  List<String> list;
    Bundle xx;
    JSONArray jarry;

    private AccessoriesAdapter mAdapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

           xx=savedInstanceState;

            Intent intent = getIntent();
            final String username = intent.getStringExtra("username");
            final String food_type = intent.getStringExtra("food_type");
            list = new ArrayList<String>();

            final Response.Listener<String> responseListener = new Response.Listener<String>(){
                @Override
                public void onResponse(String response) {
                    try {
                        Log.d(response.toString(), "onResponse: ");
                         jarry = new JSONArray(response);
                        if (true) {

                            Log.d(response.toString(), "onResponseeee: ");
                    for (int i=0;i< jarry.length();i++)
                    {

                       JSONObject jobj= jarry.getJSONObject(i);

                        String price= jobj.getString("price");
                        String location= jobj.getString("location");
                        String  food_type= jobj.getString("food_type");
                        String  food_desc= jobj.getString("food_description");
                        String food_string= "$" + price +"\n"+  location + "\n" + food_type +"\n"+ food_desc;

                        list.add(food_string);

                        Log.d(list.get(i).toString(), "list item: ");

                        if (xx != null) {
                            mStarStates = xx.getBooleanArray(STAR_STATES);
                        } else {
                            mStarStates = new boolean[list.size()];
                        }

                    }
                            }
                         else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                            builder.setMessage("Search Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mAdapter = new AccessoriesAdapter();
                    setListAdapter(mAdapter);

                }


            };
            SearchRequest SearchRequest = new SearchRequest(username,food_type,responseListener);
            RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
            queue.add(SearchRequest);

        }



        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putBooleanArray(STAR_STATES, mStarStates);
        }
        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
          // showMessage(getString(R.string.you_want_info_about_format,list.get(position).toString()));
        }

        private static class AccessoriesViewHolder {
            public CheckBox star;
            public TextView content;
        }

        private class AccessoriesAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public String getItem(int position) {
               // return CHEESES[position];
                return list.get(position).toString();
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                AccessoriesViewHolder holder = null;
               // View subView = null;

                if (convertView == null) {

                    Log.d(list.get(position).toString(), "positionsssss: ");
                 //  setContentView(R.layout.search);
                 //   convertView =getLayoutInflater().inflate(R.layout.search,parent,true);
                    ListView framestub= (ListView)findViewById(R.id.list_view);
                    convertView = getLayoutInflater().inflate(R.layout.searchstub,framestub ,false);

                    holder = new AccessoriesViewHolder();
                    holder.star = (CheckBox) convertView.findViewById(R.id.btn_star);
                    holder.star.setOnCheckedChangeListener(mStarCheckedChanceChangeListener);
                    holder.content = (TextView) convertView.findViewById(R.id.content);

                    ((Button) convertView.findViewById(R.id.btn_buy)).setOnClickListener(mBuyButtonClickListener);

                    convertView.setTag(holder);
                } else {
                    holder = (AccessoriesViewHolder)convertView.getTag();
                }

                holder.star.setChecked(mStarStates[position]);
               holder.content.setText(list.get(position).toString());

                return convertView;
            }
        }

        private void showMessage(String message) {
            Toast.makeText(SearchActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        private OnClickListener mBuyButtonClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Cyril: Not implemented yet!
                final int position = getListView().getPositionForView(v);

                Log.d(String.valueOf(position), "onClick: ");
                Log.d(list.get(position).toString(), "list item: ");
                String s=list.get(position).toString();
                String price=s.substring(s.indexOf("$") + 1, s.indexOf("\n"));
                Log.d(price, ":price");


                Intent intent = getIntent();
                final String username = intent.getStringExtra("username");
                Intent intent2 = new Intent(SearchActivity.this,GoogleWalletNB.class);

                intent.putExtra("username", username);
                intent.putExtra("price",price);
                SearchActivity.this.startActivity(intent2);

                if (position != ListView.INVALID_POSITION) {
                //    showMessage(getString(R.string.you_want_to_buy_format, CHEESES[position]));
                }


            }
        };

        private OnCheckedChangeListener mStarCheckedChanceChangeListener = new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Cyril: Not implemented yet!



            }
        };
    }

