package com.example.connector.doyeon.lib.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Trans_Pre_Sup_Request extends StringRequest {

    final static private String URL = "http://ysndy123.cafe24.com/Trans_MyPage_List_Res_Pre.php"; // 서버 URL
    private Map<String, String> parameters;

    public Trans_Pre_Sup_Request(String id, Response.Listener<String> listener) { //Constructor에서 두 번째 것 자동완성
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("ID",id);
        //서버에서 userID 키로 찾을 수 있도록 해쉬맵으로 데이터 추가
    }

    protected Map<String, String> getParams() throws AuthFailureError { //서버에 전송할 데이터 리턴
        return parameters;
    }
    //request 와 response 를 분리

}
