package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

//현황 탭
public class NowTab extends Fragment {

    TabLayout tabLayout;

    public NowTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_re_now, container, false);

        tabLayout = view.findViewById(R.id.nowtabs);
        tabLayout.addTab(tabLayout.newTab().setText("신청현황"));
        tabLayout.addTab(tabLayout.newTab().setText("거래현황"));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager nowtabviewPager = view.findViewById(R.id.nowtabviewPager);
        final NowTabPageAdapter tabPageAdapter = new NowTabPageAdapter(getChildFragmentManager(),2);
        nowtabviewPager.setAdapter(tabPageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(nowtabviewPager));
        nowtabviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        return view;

    }
}

