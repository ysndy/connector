package com.example.connector.doyeon;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.example.connector.doyeon.activity.mainview.SearchActivity;
import com.google.android.material.tabs.TabLayout;

public class Filter_dialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG_EVENT_DIALOG = "dialog_event";
    TabLayout tabs;
    Button cancleFilter, okFilter;
    FilterPagerAdapter filterPagerAdapter;

    public Filter_dialog() {
    }

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
        filterPagerAdapter = new FilterPagerAdapter(getChildFragmentManager(), 2);
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

        /* 필터적용 버튼*/
        okFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected_category="%";
                String selected_sido="%";
                String selected_gugun="%";

                try {
                    // 분야 가져오기
                    Filter_category category = filterPagerAdapter.getTab1();
                    String[] categories = getResources().getStringArray(R.array.category_list);
                    if (category.getCategory_list().getCheckedItemPosition() != 0) {
                        selected_category = "%"+categories[category.getCategory_list().getCheckedItemPosition()]+"%";
                    } else selected_category = "%";
                    Log.d("asd", "filter.category = " + categories[category.getCategory_list().getCheckedItemPosition()]);
                } catch (Exception e) {
                    selected_category = "%";
                }
                // 지역 가져오기
                //시도

                Filter_region region = filterPagerAdapter.getTab2();
                try {
                    String[] siDo = getResources().getStringArray(R.array.region_list);
                    if (region.getSiDo().getCheckedItemPosition() != 0) {
                        selected_sido = "%"+siDo[region.getSiDo().getCheckedItemPosition()]+"%";
                    } else selected_sido = "%";
                    Log.d("asd", "filter.category = " + siDo[region.getSiDo().getCheckedItemPosition()]);
                } catch (Exception e) {
                    selected_sido = "%";
                }
                //구군
                try {
                    String[][] guGun = region.getGugunArray();
                    if (region.getSiDo().getCheckedItemPosition() != 0) {
                        selected_gugun = "%"+guGun[region.getSiDo().getCheckedItemPosition()][region.getGuGun().getCheckedItemPosition()]+"%";
                    } else selected_gugun = "%";
                    Log.d("asd", "filter.category = " + guGun[region.getSiDo().getCheckedItemPosition()][region.getGuGun().getCheckedItemPosition()]);
                } catch (Exception e) {
                    selected_gugun = "%";
                }

                ((SearchActivity)getActivity()).setStr_category(selected_category);
                ((SearchActivity)getActivity()).setStr_guGun(selected_gugun);
                ((SearchActivity)getActivity()).setStr_siDo(selected_sido);

                dismiss();

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {

    }
}
