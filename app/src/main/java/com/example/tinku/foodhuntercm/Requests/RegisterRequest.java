package com.example.tinku.foodhuntercm.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Method;

/**
 * Created by sandeep on 4/15/2016.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://www.nativebites.comxa.com/register.php";
    private Map<String, String> params;
    public RegisterRequest(String email, String type, String username, String password,  Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("email",email);
        params.put("username",username);
        params.put("password",password);
        params.put("type",type);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
