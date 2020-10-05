package com.example.connector.doyeon.lib.maintab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.activity.IntentName;
import com.example.connector.doyeon.activity.MainPageActivity;
import com.example.connector.doyeon.activity.ProfileListActivity;
import com.example.connector.doyeon.activity.SupplierProfileActivity;
import com.example.connector.doyeon.lib.ProfileAdapter;
import com.example.connector.doyeon.lib.bestpager.BestPagerAdapter;
import com.example.connector.doyeon.lib.request.SupplierListRequest;
import com.example.connector.doyeon.lib.request.SupplierNewListRequest;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.supplierprofile.SupplierData1;
import com.example.connector.sampleData.supplierprofile.SupplierData2;
import com.example.connector.sampleData.supplierprofile.SupplierData3;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewProfileListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewProfileListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Profile myProfile;
    ArrayList<Profile> newProfiles;
    ListView newListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewProfileListFragment(Profile myProfile) {
        // Required empty public constructor
        this.myProfile = myProfile;
    }

    public NewProfileListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewProfileListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewProfileListFragment newInstance(String param1, String param2) {
        NewProfileListFragment fragment = new NewProfileListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_new_profile_list, container, false);
        newListView = rootView.findViewById(R.id.newListView);
        View footer = getLayoutInflater().inflate(R.layout.listfooter_main_more, null, false);
        newListView.addFooterView(footer);
        Button moreBtn = (Button) footer.findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileListActivity.class);
                intent.putExtra(IntentName.CODE, IntentName.CODE_NEW);
                startActivity(intent);
            }
        });
        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(newListView.getContext(), "touch", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(newListView.getContext(), SupplierProfileActivity.class);
                intent.putExtra(IntentName.PROFILE_SUP, (Serializable) newProfiles.get(position));
                intent.putExtra(IntentName.PROFILE_RES, (Serializable) myProfile);
                startActivity(intent);
            }
        });
        setNewProfiles();
        return rootView;
    }

    public void setNewProfiles() {

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    newProfiles = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);

                    int max = 3;
                    if(jResponse.length()<3) max = jResponse.length();
                    for(int i = 0; i < max; i++){
                        JSONObject jso = jResponse.getJSONObject(i);
                        Profile profile = new Profile();

                        profile.setId(jso.getString(IntentName.ID));
                        profile.setName(jso.getString(IntentName.NAME));
                        //profile.setMajor(jso.getString(IntentName.NAME));
                        profile.setIntroduce(jso.getString(IntentName.INFOMATION));
                        profile.setSimpleInfo(jso.getString(IntentName.INFOMATION));
                        profile.setLocation(jso.getString(IntentName.LOCATION));
                        profile.setRating(jso.getDouble(IntentName.RATING));

                        newProfiles.add(profile);

                    }
                    ProfileAdapter adapter = new ProfileAdapter(newProfiles);
                    newListView.setAdapter(adapter);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                }catch(Exception e){
                    Log.d("asd", e.toString());
                }
            }
        };

        SupplierNewListRequest supplierNewListRequest = new SupplierNewListRequest(rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(supplierNewListRequest);

//        Profile profile = new Profile();
//        profile.setId(SupplierData1.id);
//        profile.setName(SupplierData1.name);
//        profile.setMajor(SupplierData1.major);
//        profile.setRating(SupplierData1.rating);
//        profile.setSimpleInfo(SupplierData1.simpleInfo);
//        newProfiles.add(profile);
//
//        profile = new Profile();
//        profile.setId(SupplierData2.id);
//        profile.setName(SupplierData2.name);
//        profile.setMajor(SupplierData2.major);
//        profile.setRating(SupplierData2.rating);
//        profile.setSimpleInfo(SupplierData2.simpleInfo);
//        newProfiles.add(profile);
//
//        profile = new Profile();
//        profile.setId(SupplierData3.id);
//        profile.setName(SupplierData3.name);
//        profile.setMajor(SupplierData3.major);
//        profile.setRating(SupplierData3.rating);
//        profile.setSimpleInfo(SupplierData3.simpleInfo);
//        newProfiles.add(profile);

//        ProfileAdapter adapter = new ProfileAdapter(newProfiles);
//        newListView.setAdapter(adapter);
    }
}
