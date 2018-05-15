package com.example.avallejo.myapplication;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Avallejo on 2/03/2018.
 */

public class RegistroRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="http://192.168.209.63:8090/App_BD/App_register.php";
    private Map<String,String> params;
    public RegistroRequest(String APP_USER, String APP_NAME , String APP_PASS , Response.Listener<String> listener){
        super(Request.Method.POST, REGISTER_REQUEST_URL,listener,null);



        params = new HashMap<>();
        params.put("APP_USER",APP_USER);
        params.put("APP_NAME",APP_NAME);
        params.put("APP_PASS",APP_PASS);


    }
    @Override
    public Map<String, String> getParams(){
        return params;
    }
}
