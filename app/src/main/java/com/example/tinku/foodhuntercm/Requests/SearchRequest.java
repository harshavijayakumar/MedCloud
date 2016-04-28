package com.example.tinku.foodhuntercm.Requests;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;



        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by sandeep on 4/15/2016.
 */
public class SearchRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://www.nativebites.comxa.com/foodsearch.php";
    private Map<String, String> params;
    public SearchRequest(String username, String food_type,Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("food",food_type);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}

