package com.example.connector.doyeon.lib.bestpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.SupplierData1;

import java.util.ArrayList;

public class BestPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> items;
    ArrayList<Profile> profiles;
    private int pageCount = 0;

    public BestPagerAdapter(@NonNull FragmentManager fm, Profile myProfile) {
        super(fm);
        items = new ArrayList<>();
        pageCount = items.size()+1;
        setProfiles();
        for(int i=0; i<profiles.size(); i++){
            items.add(new BestProfileFragment(profiles.get(i), myProfile));

        }

    }

    private void setProfiles(){

        profiles = new ArrayList<>();
        for(int i=0; i<4; i++) {
            Profile profile = new Profile();
            profile.setId(SupplierData1.id);
            profile.setName(SupplierData1.name);
            profile.setMajor(SupplierData1.major);
            profiles.add(profile);
        }

    }

    public Profile getProfile(int index){
        return profiles.get(index);
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

    //public

}