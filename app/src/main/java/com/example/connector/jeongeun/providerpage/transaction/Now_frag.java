package com.example.connector.jeongeun.providerpage.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.dictionary.IntentName;
import com.example.connector.doyeon.lib.request.Trans_Pre_Res_Request;
import com.example.connector.doyeon.lib.request.Trans_Pre_Sup_Request;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.doyeon.util.Util;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// 공급자 마이페이지 현황
public class Now_frag extends Fragment {

    Profile myProfile;
    TabLayout tabs;
    Button pr_now_requestBtn;
    TextView pr_now_requestName, pr_now_requestDate;
    ListView now_request_list;
    ArrayList<Transaction> transactions;


    public Now_frag(Profile myProfile) {
        // Required empty public constructor
        this.myProfile = myProfile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.fragment_now_frag_je, container, false);

        View v = inflater.inflate(R.layout.fragment_now_request, container, false);

        pr_now_requestBtn = v.findViewById(R.id.pr_now_requestBtn);
        now_request_list = v.findViewById(R.id.now_request_list);

        // 임시 데이터

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    transactions = new ArrayList<>();
                    Util.printingLog(Now_frag.this.getClass().getSimpleName(), response);
                    Util.printingLog(Now_frag.this.getClass().getSimpleName(), myProfile.getId());

                    JSONArray jResponse = new JSONArray(response);
                    int max = jResponse.length();
                    for (int i = 0; i < max; i++) {
                        JSONObject jso = jResponse.getJSONObject(i);
                        Transaction transaction = new Transaction();
                        transaction.setProviderName(jso.getString(IntentName.NAME));
                        transaction.setDate(jso.getString(IntentName.DATE));
                        transaction.setProviderID(jso.getString(IntentName.ID));
                        transactions.add(transaction);

                    }

                    Now_list now_request_adapter = new Now_list(transactions);
                    now_request_list.setAdapter(now_request_adapter);

                } catch (Exception e) {
                    Util.printingLog(Now_frag.this.getClass().getSimpleName(), e.toString());
                    //Log.d("asd", "Request.java - " + e.toString());
                }
            }
        };

        if(myProfile.getLoginCode()==IntentName.CODE_SUP) {
            Trans_Pre_Res_Request transPreResRequest = new Trans_Pre_Res_Request(myProfile.getId(), rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(transPreResRequest);

        }else if(myProfile.getLoginCode()==IntentName.CODE_RES){
            Trans_Pre_Sup_Request transPreSupRequest = new Trans_Pre_Sup_Request(myProfile.getId(), rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(transPreSupRequest);
        }
        return v;
    }
}

