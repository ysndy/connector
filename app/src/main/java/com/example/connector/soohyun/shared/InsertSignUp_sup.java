package com.example.connector.soohyun.shared;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertSignUp_sup extends StringRequest {

    final static private  String URL = "http://";
    private Map<String, String> parameters;

    public InsertSignUp_sup(String name, String supplierID, String passWord, String email, String callnumber, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("supplierID", supplierID);
        parameters.put("passWord", passWord);
        parameters.put("email", email);
        parameters.put("callnumber", callnumber);
    }

    protected Map<String, String> getParms() throws AuthFailureError {
        Log.d("asd", "return-Insert");
        return parameters;
    }
}
