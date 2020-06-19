package com.example.connector.soohyun.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.jeongeun.Now_deal;
import com.example.connector.jeongeun.Now_request;

public class TabPageAdapter extends FragmentPagerAdapter {

    int num;

    public TabPageAdapter(@NonNull FragmentManager fragmentPagerAdapter, int tabnum){
       super(fragmentPagerAdapter);
        this.num = tabnum;
    }

    @NonNull
    @Override
    public Fragment getItem(int position){
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


