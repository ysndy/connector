package com.example.connector.doyeon.activity.mainview.maintab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class MainTabPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> items;
    public MainTabPagerAdapter(@NonNull FragmentManager fm, Profile myProfile) {
        super(fm);
        items = new ArrayList<>();
        items.add(new NewProfileListFragment(myProfile));
        items.add(new RecommendProfileListFragment(myProfile));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "NEW";
            case 1:
                return "추천";
            default:
                return null;
        }
    }
}
