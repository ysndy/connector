package com.example.connector.jeongeun.providerpage.follow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.dictionary.IntentName;
import com.example.connector.doyeon.lib.request.Follow_List_Res_Request;
import com.example.connector.doyeon.lib.request.Follow_List_Sup_Request;
import com.example.connector.doyeon.objects.Profile;

import com.example.connector.jeongeun.providerpage.provider.Restaurant_info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// 공급자 마이페이지 팔로워
public class Follower_frag extends Fragment {

    ListView follower_list; // 팔로워 리스트
    Profile myProfile;

    public Follower_frag(Profile myProfile) {
        // Required empty public constructor
        this.myProfile = myProfile;
    }

    @Override
    public void onResume() {
        super.onResume();
        setFollower_list();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_follower_frag, container, false);

        follower_list = v.findViewById(R.id.follower_list);


        // 임시 데이터

        // 외식업자 프로필 이동
        follower_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Restaurant_info.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public void setFollower_list(){

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Profile> profiles = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);
                    int max = jResponse.length();
                    Log.d("asd", myProfile.getMajor() + jResponse.length());
                    for (int i = 0; i < max; i++) {
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
                    Follower_list follower_list_adapter = new Follower_list(profiles);
                    follower_list.setAdapter(follower_list_adapter);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                } catch (Exception e) {
                    Log.d("asd", e.toString());
                }
            }
        };

        if (myProfile.getLoginCode() == IntentName.CODE_SUP) {
            Follow_List_Sup_Request follow_list_sup_request = new Follow_List_Sup_Request(myProfile.getId(), rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(follow_list_sup_request);

        } else if (myProfile.getLoginCode() == IntentName.CODE_RES) {
            Follow_List_Res_Request follow_list_res_request = new Follow_List_Res_Request(myProfile.getId(), rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(getActivity());
            queue.add(follow_list_res_request);
        }


    }

}
