package com.example.connector.soohyun.tabs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NowTabPageAdapter extends FragmentPagerAdapter {

    int num;

    public NowTabPageAdapter(@NonNull FragmentManager fragmentPagerAdapter, int tabnum){
       super(fragmentPagerAdapter);
        this.num = tabnum;
    }

    @NonNull
    @Override
    public Fragment getItem(int position){
        switch (position) {
            case 0:
                NowTab_request tab1 = new NowTab_request();
                return tab1;
            case 1:
                NowTab_ing tab2 = new NowTab_ing();
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


