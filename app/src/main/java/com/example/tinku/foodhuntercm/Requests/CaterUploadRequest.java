package com.example.tinku.foodhuntercm.Requests;


import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.tinku.foodhuntercm.CaterUpload;

import java.util.HashMap;
import java.util.Map;
/**
 * Created by sandeep on 4/19/2016.
 */
public class CaterUploadRequest extends StringRequest {
    private static final String CATERUPLOAD_REQUEST_URL = "http://www.nativebites.comxa.com/caterupload.php";
    private Map<String, String> params;
    public CaterUploadRequest(String uname, String foodtype, String caterLocation, String caterFood, float foodPrice, int caterContact, Response.Listener<String> listener){
        super(Method.POST, CATERUPLOAD_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("cusername", uname);
        params.put("foodtype"    ,  foodtype);
        params.put("caterLocation",  caterLocation);
        params.put("caterFood"    ,  caterFood);
        params.put("foodPrice", foodPrice + "");
        params.put("caterContact" ,  caterContact+"");
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
