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

// 공급자 마이페이지 팔로워
public class Follower_frag extends Fragment {

    ListView follower_list; // 팔로워 리스트

    public Follower_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_follower_frag, container, false);

        follower_list = v.findViewById(R.id.follower_list);

        // 임시 데이터
        ArrayList<Profile> profiles;
        profiles = new ArrayList<>();
        Profile profile1 = new Profile();

        profile1.setName(RestaurantData1.name);
        profile1.setMajor(RestaurantData1.major);
        profile1.setLocation(RestaurantData1.location);

        profiles.add(profile1);


        Follower_list follower_list_adapter = new Follower_list(profiles);
        follower_list.setAdapter(follower_list_adapter);

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
}
