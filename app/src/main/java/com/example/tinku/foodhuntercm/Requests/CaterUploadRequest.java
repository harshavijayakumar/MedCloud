package com.example.tinku.foodhuntercm.Requests;

/* Import appropriate libraries */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/* Caterupload request to server */
public class CaterUploadRequest extends StringRequest {
    private static final String CATERUPLOAD_REQUEST_URL = "http://www.nativebites.comxa.com/caterupload.php";
    private Map<String, String> params;

    /* Form the params and send the POST request to server */
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
