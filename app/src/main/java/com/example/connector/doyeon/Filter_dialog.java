package com.example.connector.doyeon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

public class Filter_dialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG_EVENT_DIALOG = "dialog_event";
    TabLayout tabs;
    Button cancleFilter, okFilter;

    public Filter_dialog() {}

    public static Filter_dialog getInstance() {
        Filter_dialog e = new Filter_dialog();
        return e;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.filter_dialog, container, false);

        cancleFilter = v.findViewById(R.id.cancleFilter);
        okFilter = v.findViewById(R.id.okFilter);

        tabs = v.findViewById(R.id.filterTab);
        tabs.addTab(tabs.newTab().setText("분야"));
        tabs.addTab(tabs.newTab().setText("지역"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager filterViewPager = v.findViewById(R.id.filterViewPager);
        final FilterPagerAdapter filterPagerAdapter = new FilterPagerAdapter(getChildFragmentManager(), 2);
        filterViewPager.setAdapter(filterPagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(filterViewPager));
        filterViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        // 취소 버튼
        cancleFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        /* 필터적용 버튼
        okFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
