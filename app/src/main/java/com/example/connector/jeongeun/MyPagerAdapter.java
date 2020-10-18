package com.example.connector.jeongeun;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs; // 탭의 개수

    public MyPagerAdapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm);
        this.mNumOfTabs = numTabs;
    }

    // position 값에 해당하는 fragment를 반환
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Item_frag tab1 = new Item_frag();
                return tab1;
            case 1:
                Follower_frag tab2 = new Follower_frag();
                return tab2;
            case 2:
                Provider_frag tab3 = new Provider_frag();
                return tab3;
            case 3:
                Now_frag tab4 = new Now_frag();
                return tab4;
            default:
                return null;
        }
        //return null;
    }

    // tab의 수 반환
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
