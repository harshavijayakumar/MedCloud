package com.example.tinku.foodhuntercm.Requests;

/* Import appropriate libraries */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeep on 4/15/2016.
 */
/* Login user request to server */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://www.nativebites.comxa.com/login.php";
    private Map<String, String> params;

    /* Form the params and send the POST request to server */
    public LoginRequest(String username, String password, Response.Listener<String> listener){
        super(Method.POST, LOGIN_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
