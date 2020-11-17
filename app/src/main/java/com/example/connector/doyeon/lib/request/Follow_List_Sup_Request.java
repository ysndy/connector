package com.example.connector.doyeon.lib.request;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.connector.doyeon.dictionary.IntentName;

import java.util.HashMap;
import java.util.Map;

public class Follow_List_Sup_Request extends StringRequest {

    final static private String URL = "http://ysndy123.cafe24.com/Follow_List_Sup.php"; // 서버 URL
    private Map<String, String> parameters;

    public Follow_List_Sup_Request(String id, Response.Listener<String> listener) { //Constructor에서 두 번째 것 자동완성
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put(IntentName.ID, id);
        //서버에서 userID 키로 찾을 수 있도록 해쉬맵으로 데이터 추가
    }

    protected Map<String, String> getParams() throws AuthFailureError { //서버에 전송할 데이터 리턴
        Log.d("asd", "return-Test");
        return parameters;
    }
    //request 와 response 를 분리

}
