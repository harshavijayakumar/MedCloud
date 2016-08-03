package com.example.tinku.foodhuntercm.Requests;

/* Import appropriate libraries */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.tinku.foodhuntercm.Entities.User;
import java.util.HashMap;
import java.util.Map;

/* Register user request to server */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://104.155.175.65/register.php";
    private Map<String, String> params;

    /* Form the params and send the POST request to server */
    public RegisterRequest(User userObj,  Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("username",userObj.getUsername());
        params.put("password",userObj.getPassword());
        params.put("email",userObj.getUserEmail());
        params.put("type",userObj.getUserType());
    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
