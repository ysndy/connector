package com.example.connector.doyeon.activity.mainview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.Filter_dialog;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.lib.ProfileAdapter;
import com.example.connector.doyeon.lib.request.SupplierNewListRequest;
import com.example.connector.doyeon.lib.request.SupplierSchListRequest;
import com.example.connector.doyeon.objects.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends FragmentActivity {

    ListView profileListView;
    EditText searchET;
    ImageView searchBtn;
    Button detailSettingBtn;
    String str_category ="%", str_siDo ="%", str_guGun="%", str_name="%";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        profileListView = findViewById(R.id.profileListView);
        searchBtn = findViewById(R.id.searchBtn);
        searchET = findViewById(R.id.searchET);
        detailSettingBtn = findViewById(R.id.detailSettingBtn);

        detailSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter_dialog fd = Filter_dialog.getInstance();
                fd.show(getSupportFragmentManager(), Filter_dialog.TAG_EVENT_DIALOG);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Log.d("asd", str_category + " " + str_siDo + " " + str_guGun);
                    str_name = "%"+searchET.getText().toString()+"%";

                    //서버에서 검색 후 리스트 출력
                    Response.Listener rListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                ArrayList<Profile> profiles = new ArrayList<>();
                                JSONArray jResponse = new JSONArray(response);

                                int max = 30;
                                if (jResponse.length() < 30) max = jResponse.length();
                                for (int i = 0; i < max; i++) {
                                    JSONObject jso = jResponse.getJSONObject(i);
                                    Profile profile = new Profile();

                                    profile.setId(jso.getString(IntentName.ID));
                                    profile.setName(jso.getString(IntentName.NAME));
                                    profile.setMajor(jso.getString(IntentName.CATEGORY));
                                    profile.setIntroduce(jso.getString(IntentName.INFOMATION));
                                    profile.setSimpleInfo(jso.getString(IntentName.INFOMATION));
                                    profile.setLocation(jso.getString(IntentName.LOCATION));
                                    profile.setRating(jso.getDouble(IntentName.RATING));

                                    profiles.add(profile);
                                    Log.d("asd", "searchResult: "+jso.getString(IntentName.NAME));

                                }
                                ProfileAdapter adapter = new ProfileAdapter(profiles);
                                profileListView.setAdapter(adapter);
                                //adapter.notifyDataSetChanged();
                                //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                                //Log.d("asd", "jResponse"+jResponse.);

                            } catch (Exception e) {
                                Log.d("asd", e.toString());
                            }
                        }
                    };

                    SupplierSchListRequest supplierSchListRequest = new SupplierSchListRequest(str_category, str_siDo, str_guGun, str_name, rListener); //Request 처리 클래스
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    queue.add(supplierSchListRequest);

                }catch(Exception e){}

            }
        });

    }

    public String getStr_category() {
        return str_category;
    }

    public void setStr_category(String str_category) {
        this.str_category = str_category;
    }

    public String getStr_siDo() {
        return str_siDo;
    }

    public void setStr_siDo(String str_siDo) {
        this.str_siDo = str_siDo;
    }

    public String getStr_guGun() {
        return str_guGun;
    }

    public void setStr_guGun(String str_guGun) {
        this.str_guGun = str_guGun;
    }
}
