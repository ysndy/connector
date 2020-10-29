package com.example.connector.jeongeun.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.jeongeun.providerpage.Restaurant_info;
import com.example.connector.sampleData.restaurantprofile.RestaurantData1;

import java.util.ArrayList;

// 공급자 마이페이지 거래처
public class Provider_frag extends Fragment {

    ListView provider_list; // 거래처 리스트

    public Provider_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_provider_frag, container, false);

        provider_list = v.findViewById(R.id.provider_list);


        // 임시 데이터
        ArrayList<Profile> profiles;
        profiles = new ArrayList<>();
        Profile profile1 = new Profile();

        profile1.setName(RestaurantData1.name);
        profile1.setCallNumber(RestaurantData1.callNumber); // [연락처 : ---]로 수정
        profile1.setLocation(RestaurantData1.location); // 수정, 총매출로 바꿔야함

        profiles.add(profile1);


        Provider_list provider_list_adapter = new Provider_list(profiles);
        provider_list.setAdapter(provider_list_adapter);

        provider_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Restaurant_info.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
