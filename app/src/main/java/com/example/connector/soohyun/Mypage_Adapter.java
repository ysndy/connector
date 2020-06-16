package com.example.connector.soohyun;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Mypage_Adapter extends FragmentPagerAdapter {
    int mNumOfTabs; // 탭의 개수

    public Mypage_Adapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm);
        this.mNumOfTabs = numTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                item_frag tab1 = new item_frag();
                return tab1;
            case 1:
                following_frag tab2 = new following_frag();
                return tab2;
            case 2:
                account_frag tab3 = new account_frag();
                return tab3;
            case 3:
                now_frag tab4 = new now_frag();
                return tab4;
            default:
                return null;
        }
        //return null;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}