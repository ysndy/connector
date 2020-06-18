package com.example.connector.soohyun;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.jeongeun.Follower_frag;
import com.example.connector.jeongeun.Item_frag;
import com.example.connector.jeongeun.Now_frag;
import com.example.connector.jeongeun.Provider_frag;

public class rest_MypageAdapter extends FragmentPagerAdapter {

    int numTab;
    public rest_MypageAdapter(@NonNull FragmentManager fragmentManager, int numTabs) {
        super(fragmentManager);
        this.numTab = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               reLike_frag tab1 = new reLike_frag();
               return  tab1;
            case 1:
                reFollowing_frag tab2 = new reFollowing_frag();
                return tab2;
            case 2:
                reGorae_frag tab3 = new reGorae_frag();
                return tab3;
            case 3:
                reNow_frag tab4 = new reNow_frag();
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
