package com.example.connector.jeongeun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.connector.R;

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

        // Inflate the layout for this fragment
        return v;
    }
}
