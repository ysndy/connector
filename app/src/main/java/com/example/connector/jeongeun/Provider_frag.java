package com.example.connector.jeongeun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.connector.R;

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


        // Inflate the layout for this fragment
        return v;
    }
}
