package com.example.connector.doyeon.lib;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ValidateRequest extends StringRequest {

    final static private String URL = "http://10.0.2.2/2018081093/ValidateTest.php"; // 서버 URL
    private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener) { //Constructor에서 두 번째 것 자동완성
        super(Method.POST, URL, listener, null);

        Log.d("asd", "vr--");
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        //서버에서 userID 키로 찾을 수 있도록 해쉬맵으로 데이터 추가
    }

    protected Map<String, String> getParams() throws AuthFailureError { //서버에 전송할 데이터 리턴
        Log.d("asd", "return--");
        return parameters;
    }
    //request 와 response 를 분리

}
