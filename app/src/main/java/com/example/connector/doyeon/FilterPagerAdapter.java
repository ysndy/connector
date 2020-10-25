package com.example.connector.doyeon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FilterPagerAdapter extends FragmentPagerAdapter {
    int numOfTabs; // 탭의 개수

    public FilterPagerAdapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm);
        this.numOfTabs = numTabs;
    }

    // position 값에 해당하는 fragment를 반환
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Filter_category tab1 = new Filter_category();
                return tab1;
            case 1:
                Filter_region tab2 = new Filter_region();
                return tab2;
            default:
                return null;
        }
    }

    // 탭의 수 반환
    @Override
    public int getCount() {
        return numOfTabs;
    }
}
