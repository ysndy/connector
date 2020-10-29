package com.example.connector.jeongeun.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

// 공급자 마이페이지 현황
public class Now_frag extends Fragment {

    TabLayout tabs;

    public Now_frag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_now_frag_je, container, false);

        // TabLayout
        tabs = v.findViewById(R.id.minitab);
        tabs.addTab(tabs.newTab().setText("신청현황"));
        tabs.addTab(tabs.newTab().setText("거래현황"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);


        final ViewPager miniviewPager = v.findViewById(R.id.miniviewPager);
        final MiniPagerAdapter myPagerAdapter = new MiniPagerAdapter(getChildFragmentManager(), 2);
        miniviewPager.setAdapter(myPagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(miniviewPager));
        miniviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        return v;
    }
}

