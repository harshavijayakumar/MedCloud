package com.example.tinku.foodhuntercm.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeep on 4/19/2016.
 */
public class DinerUploadRequest extends StringRequest {
    private static final String DINERUPLOAD_REQUEST_URL = "http://www.nativebites.comxa.com/dinerupload.php";
    private Map<String, String> params;
    public DinerUploadRequest(String uname, String location, int contact, Response.Listener<String> listener){
        super(Method.POST, DINERUPLOAD_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("uname",uname);
        params.put("location",location);
        params.put("contact", contact+"");
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
