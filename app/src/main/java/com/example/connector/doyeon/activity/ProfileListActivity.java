package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.lib.ProfileAdapter;
import com.example.connector.doyeon.lib.request.SupplierNewListRequest;
import com.example.connector.doyeon.lib.request.SupplierRecListRequest;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.supplierprofile.SupplierData1;
import com.example.connector.sampleData.supplierprofile.SupplierData10;
import com.example.connector.sampleData.supplierprofile.SupplierData2;
import com.example.connector.sampleData.supplierprofile.SupplierData3;
import com.example.connector.sampleData.supplierprofile.SupplierData4;
import com.example.connector.sampleData.supplierprofile.SupplierData5;
import com.example.connector.sampleData.supplierprofile.SupplierData6;
import com.example.connector.sampleData.supplierprofile.SupplierData7;
import com.example.connector.sampleData.supplierprofile.SupplierData8;
import com.example.connector.sampleData.supplierprofile.SupplierData9;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class ProfileListActivity extends Activity {

    private ArrayList<Profile> profiles;
    private Profile myProfile;
    private String resMajor;
    private int listCode;

    private ListView profileListView;
    private TextView topTextView;

    /*
     * 실행시킨 버튼이 Top, New, Recommend 중 무엇인지 확인
     * TextView 변경
     * 서버데이터베이스
     * listCode 확인
     * LIST_TOP - 별점순으로 소팅하고 profiles에 데이터 10개 저장
     * LIST_NEW - profiles에 데이터 10개 저장
     * LIST_RECOMMEND - resMajor 조건검색으로 데이터 10개 저장
     *
     * */
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilelist);
        inflating();
        intent = getIntent();
        myProfile = intent.getParcelableExtra(IntentName.PROFILE);
        int code = intent.getIntExtra(IntentName.CODE, 0);
        if (code == IntentName.CODE_NEW) setProfilesNew();
        else recommendListSet();

        //인텐트 가져와야댐댐

        profileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(newListView.getContext(), "touch", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SupplierProfileActivity.class);
                intent.putExtra(IntentName.PROFILE_SUP, profiles.get(position));
                intent.putExtra(IntentName.PROFILE_RES, myProfile);
                startActivity(intent);
            }
        });

    }

    private void inflating() {

        profileListView = findViewById(R.id.profileListView);
        topTextView = findViewById(R.id.topTextView);

    }

    private void topListSet() {

    }

    private void newListSet() {

    }

    private void recommendListSet() {

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    profiles = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);

                    int max = 30;
                    if (jResponse.length() < 30) max = jResponse.length();
                    for (int i = 0; i < max; i++) {
                        JSONObject jso = jResponse.getJSONObject(i);
                        Profile profile = new Profile();

                        profile.setId(jso.getString(IntentName.ID));
                        profile.setName(jso.getString(IntentName.NAME));
                        //profile.setMajor(jso.getString(IntentName.NAME));
                        profile.setIntroduce(jso.getString(IntentName.INFOMATION));
                        profile.setSimpleInfo(jso.getString(IntentName.INFOMATION));
                        profile.setLocation(jso.getString(IntentName.LOCATION));
                        profile.setRating(jso.getDouble(IntentName.RATING));

                        profiles.add(profile);

                    }
                    ProfileAdapter adapter = new ProfileAdapter(profiles);
                    profileListView.setAdapter(adapter);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                } catch (Exception e) {
                    Log.d("asd", e.toString());
                }
            }
        };

        SupplierRecListRequest supplierRecListRequest = new SupplierRecListRequest(myProfile.getMajor(), rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(supplierRecListRequest);
    }

    private void setProfilesNew() {

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    profiles = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);

                    int max = 30;
                    if (jResponse.length() < 30) max = jResponse.length();
                    for (int i = 0; i < max; i++) {
                        JSONObject jso = jResponse.getJSONObject(i);
                        Profile profile = new Profile();

                        profile.setId(jso.getString(IntentName.ID));
                        profile.setName(jso.getString(IntentName.NAME));
                        //profile.setMajor(jso.getString(IntentName.NAME));
                        profile.setIntroduce(jso.getString(IntentName.INFOMATION));
                        profile.setSimpleInfo(jso.getString(IntentName.INFOMATION));
                        profile.setLocation(jso.getString(IntentName.LOCATION));
                        profile.setRating(jso.getDouble(IntentName.RATING));

                        profiles.add(profile);

                    }
                    ProfileAdapter adapter = new ProfileAdapter(profiles);
                    profileListView.setAdapter(adapter);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                } catch (Exception e) {
                    Log.d("asd", e.toString());
                }
            }
        };

        SupplierNewListRequest supplierNewListRequest = new SupplierNewListRequest(rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(supplierNewListRequest);
    }

    private void setProfiles() {

        profiles = new ArrayList<>();

        Profile profile = new Profile();
        profile.setId(SupplierData1.id);
        profile.setName(SupplierData1.name);
        profile.setMajor(SupplierData1.major);
        profile.setIntroduce(SupplierData1.introduce);
        profile.setLocation(SupplierData1.location);
        profile.setRating(SupplierData1.rating);
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData2.id);
        profile.setName(SupplierData2.name);
        profile.setMajor(SupplierData2.major);
        profile.setIntroduce(SupplierData2.introduce);
        profile.setLocation(SupplierData2.location);
        profile.setRating(SupplierData2.rating);

        profiles.add(profile);
        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData3.id);
        profile.setName(SupplierData3.name);
        profile.setMajor(SupplierData3.major);
        profile.setIntroduce(SupplierData3.introduce);
        profile.setLocation(SupplierData3.location);
        profile.setRating(SupplierData3.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData4.id);
        profile.setName(SupplierData4.name);
        profile.setMajor(SupplierData4.major);
        profile.setIntroduce(SupplierData4.introduce);
        profile.setLocation(SupplierData4.location);
        profile.setRating(SupplierData4.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData5.id);
        profile.setName(SupplierData5.name);
        profile.setMajor(SupplierData5.major);
        profile.setIntroduce(SupplierData5.introduce);
        profile.setLocation(SupplierData5.location);
        profile.setRating(SupplierData5.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData6.id);
        profile.setName(SupplierData6.name);
        profile.setMajor(SupplierData6.major);
        profile.setIntroduce(SupplierData6.introduce);
        profile.setLocation(SupplierData6.location);
        profile.setRating(SupplierData6.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData7.id);
        profile.setName(SupplierData7.name);
        profile.setMajor(SupplierData7.major);
        profile.setIntroduce(SupplierData7.introduce);
        profile.setLocation(SupplierData7.location);
        profile.setRating(SupplierData7.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData8.id);
        profile.setName(SupplierData8.name);
        profile.setMajor(SupplierData8.major);
        profile.setIntroduce(SupplierData8.introduce);
        profile.setLocation(SupplierData8.location);
        profile.setRating(SupplierData8.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData9.id);
        profile.setName(SupplierData9.name);
        profile.setMajor(SupplierData9.major);
        profile.setIntroduce(SupplierData9.introduce);
        profile.setLocation(SupplierData9.location);
        profile.setRating(SupplierData9.rating);
        profiles.add(profile);

        profile = new Profile();
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        profile.setId(SupplierData10.id);
        profile.setName(SupplierData10.name);
        profile.setMajor(SupplierData10.major);
        profile.setIntroduce(SupplierData10.introduce);
        profile.setLocation(SupplierData10.location);
        profile.setRating(SupplierData10.rating);
        profiles.add(profile);


    }

}
