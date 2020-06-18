package com.example.connector.doyeon.lib.bestpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.doyeon.lib.profiletab.ProductListFragment;
import com.example.connector.doyeon.lib.profiletab.SupplierInfoFragment;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.sampleData.SupplierData1;

import java.util.ArrayList;

public class BestPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> items;
    ArrayList<Profile> profiles;

    public BestPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        items = new ArrayList<>();
        setProfiles();
        for(int i=0; i<4; i++){
            Profile profile = new Profile();
            profile.setId(SupplierData1.id);
            profile.setName(SupplierData1.name);
            profile.setMajor(SupplierData1.major);
            items.add(new BestProfileFragment());
        }

    }

    private void setProfiles(){

        for(int i=0; i<1; i++) {

            Profile profile = new Profile();
            profile.setId(SupplierData1.id);
            profile.setName(SupplierData1.name);
            profile.setMajor(SupplierData1.major);
        }

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

}
