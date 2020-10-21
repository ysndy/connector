package com.example.connector.doyeon.lib.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InsertTransactionRequest extends StringRequest {

    final static private String URL = "http://ysndy123.cafe24.com/InsertTransaction.php"; // 서버 URL
    private Map<String, String> parameters;

    public InsertTransactionRequest(String supplierID, String restaurantID, String date, String state, String productCode, String count, Response.Listener<String> listener) { //Constructor에서 두 번째 것 자동완성
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("supplierID", supplierID);
        parameters.put("restaurantID", restaurantID);
        parameters.put("date", date);
        parameters.put("state", state);
        parameters.put("productCode", productCode);
        parameters.put("count", count);
        //서버에서 userID 키로 찾을 수 있도록 해쉬맵으로 데이터 추가
    }

    protected Map<String, String> getParams() throws AuthFailureError { //서버에 전송할 데이터 리턴
        Log.d("asd", "return-Test");
        return parameters;
    }
    //request 와 response 를 분리

}
