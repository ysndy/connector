package com.example.connector.jeongeun;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class MiniPagerAdapter extends FragmentPagerAdapter {

    int num;

    public MiniPagerAdapter(@NonNull FragmentManager fm, int numTab) {
        super(fm);
        this.num = numTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Now_request tab1 = new Now_request();
                return tab1;
            case 1:
                Now_deal tab2 = new Now_deal();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num;
    }
}
