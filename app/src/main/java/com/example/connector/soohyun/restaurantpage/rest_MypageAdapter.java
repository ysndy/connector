package com.example.connector.soohyun.restaurantpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.soohyun.tabs.reFollowing;
import com.example.connector.soohyun.tabs.reGorae;
import com.example.connector.soohyun.tabs.reLike;
import com.example.connector.soohyun.tabs.reNow;

public class rest_MypageAdapter extends FragmentPagerAdapter {

    int numTab;

    public rest_MypageAdapter(@NonNull FragmentManager fragmentManager, int numTabs) {
        super(fragmentManager);
        this.numTab = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               reLike tab1 = new reLike();
               return  tab1;
            case 1:
                reFollowing tab2 = new reFollowing();
                return tab2;
            case 2:
                reGorae tab3 = new reGorae();
                return tab3;
            case 3:
                reNow tab4 = new reNow();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTab;
    }
}
