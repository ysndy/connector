package com.example.connector.soohyun.restaurantpage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.soohyun.tabs.FollowingTab;
import com.example.connector.soohyun.tabs.GoraeTab;
import com.example.connector.soohyun.tabs.LikeTab;
import com.example.connector.soohyun.tabs.NowTab;

//외식업자 마이페이지 어댑터
public class MypageAdapter extends FragmentPagerAdapter {

    int numTab;

    public MypageAdapter(@NonNull FragmentManager fragmentManager, int numTabs) {
        super(fragmentManager);
        this.numTab = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
               LikeTab tab1 = new LikeTab(); //좋아요탭
               return  tab1;
            case 1:
                FollowingTab tab2 = new FollowingTab(); //팔로잉탭
                return tab2;
            case 2:
                GoraeTab tab3 = new GoraeTab(); //거래처탭
                return tab3;
            case 3:
                NowTab tab4 = new NowTab(); //현황 탭
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
