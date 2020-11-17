package com.example.connector.jeongeun.providerpage.provider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.dictionary.IntentName;
import com.example.connector.doyeon.lib.request.Trans_List_Res_Request;
import com.example.connector.doyeon.lib.request.Trans_List_Sup_Request;
import com.example.connector.doyeon.objects.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// 공급자 마이페이지 거래처
public class Provider_frag extends Fragment {

    ListView provider_list; // 거래처 리스트
    Profile myProfile;

    public Provider_frag(Profile myProfile) {
        // Required empty public constructor
        this.myProfile = myProfile;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_provider_frag, container, false);

        provider_list = v.findViewById(R.id.provider_list);

        //서버에서 데이터 가져옴
        setProvider_list();


        return v;
    }

    public void setProvider_list() {
        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Profile> profiles = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);

                    for (int i = 0; i < jResponse.length(); i++) {
                        JSONObject jso = jResponse.getJSONObject(i);
                        Profile profile = new Profile();

                        profile.setId(jso.getString(IntentName.ID));
                        profile.setName(jso.getString(IntentName.NAME));
                        //profile.setMajor(jso.getString(IntentName.RECOMMENDS));
                        profile.setIntroduce(jso.getString(IntentName.INFOMATION));
                        profile.setSimpleInfo(jso.getString(IntentName.INFOMATION));
                        profile.setLocation(jso.getString(IntentName.LOCATION));
                        profile.setCallNumber(jso.getString(IntentName.CALLNUMBER));
                        profile.setEmail(jso.getString(IntentName.EMAIL));
                        profiles.add(profile);

                    }
                    Provider_list provider_list_adapter = new Provider_list(profiles);
                    provider_list.setAdapter(provider_list_adapter);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                } catch (Exception e) {
                    Log.d("asd", e.toString());
                }
            }
        };

        if (myProfile.getLoginCode() == IntentName.CODE_SUP) {
            Trans_List_Res_Request transResRequest = new Trans_List_Res_Request(myProfile.getId(), rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(transResRequest);

        } else if (myProfile.getLoginCode() == IntentName.CODE_RES) {
            Trans_List_Sup_Request trans_list_sup_request = new Trans_List_Sup_Request(myProfile.getId(), rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(trans_list_sup_request);
        }
    }

}
