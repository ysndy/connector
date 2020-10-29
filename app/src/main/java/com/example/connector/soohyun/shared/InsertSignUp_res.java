package com.example.connector.soohyun.shared;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertSignUp_res extends StringRequest {

    final static private  String URL = "http://ysndy123.cafe24.com/SignUp_res.php";
    private Map<String, String> parameters;

    public InsertSignUp_res(String name, String restaurantID, String passWord, String email, String callnumber, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("restaurantID", restaurantID);
        parameters.put("passWord", passWord);
        parameters.put("email", email);
        parameters.put("callNumber", callnumber);
    }

    protected Map<String, String> getParams() throws AuthFailureError {
        Log.d("asd", "return-Insert");
        return parameters;
    }

}
