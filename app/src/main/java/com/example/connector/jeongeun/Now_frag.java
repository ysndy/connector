package com.example.connector.jeongeun;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;


public class Now_frag extends Fragment {

    TabLayout tabs;

    public Now_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View fv = inflater.inflate(R.layout.fragment_now_frag, container, false);
        return inflater.inflate(R.layout.fragment_now_frag, container, false);


/*
        //Tablayout
        tabs = tabs.findViewById(R.id.minitab);
        tabs.addTab(tabs.newTab().setText("신청현황"));
        tabs.addTab(tabs.newTab().setText("거래현황"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);*/


        /*final ViewPager miniviewPager = findViewById(R.id.miniviewPager);
        final MiniPagerAdapter myPagerAdapter = new MiniPagerAdapter(getSupportFragmentManager(), 2);
        miniviewPager.setAdapter(myPagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(miniviewPager));
        miniviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs)); */

        //return fv;
    }
}

