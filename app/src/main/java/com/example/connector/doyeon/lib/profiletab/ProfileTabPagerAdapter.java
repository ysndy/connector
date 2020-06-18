package com.example.connector.doyeon.lib.profiletab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.doyeon.lib.maintab.NewProfileListFragment;
import com.example.connector.doyeon.lib.maintab.RecommendProfileListFragment;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class ProfileTabPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> items;

    public ProfileTabPagerAdapter(@NonNull FragmentManager fm, Profile profile) {
        super(fm);
        items = new ArrayList<>();
        items.add(new ProductListFragment(profile));
        items.add(new SupplierInfoFragment(profile));
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
                return "상품";
            case 1:
                return "정보";
            default:
                return null;
        }
    }
}
