package com.example.connector.doyeon.lib.bestpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.supplierprofile.SupplierData1;
import com.example.connector.sampleData.supplierprofile.SupplierData10;
import com.example.connector.sampleData.supplierprofile.SupplierData2;
import com.example.connector.sampleData.supplierprofile.SupplierData3;
import com.example.connector.sampleData.supplierprofile.SupplierData4;
import com.example.connector.sampleData.supplierprofile.SupplierData5;
import com.example.connector.sampleData.supplierprofile.SupplierData6;
import com.example.connector.sampleData.supplierprofile.SupplierData7;
import com.example.connector.sampleData.supplierprofile.SupplierData8;
import com.example.connector.sampleData.supplierprofile.SupplierData9;

import java.util.ArrayList;

public class BestPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> items;
    ArrayList<Profile> profiles;
    private int pageCount = 0;

    public BestPagerAdapter(@NonNull FragmentManager fm, Profile myProfile) {
        super(fm);
        items = new ArrayList<>();
        pageCount = items.size() + 1;
        setProfiles();
        for (int i = 0; i < profiles.size(); i++) {
            items.add(new BestProfileFragment(profiles.get(i), myProfile));

        }

    }

    private void setProfiles() {

        profiles = new ArrayList<>();

        Profile profile = new Profile();
        profile.setId(SupplierData1.id);
        profile.setName(SupplierData1.name);
        profile.setMajor(SupplierData1.major);
        profile.setIntroduce(SupplierData1.introduce);
        profile.setLocation(SupplierData1.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData2.id);
        profile.setName(SupplierData2.name);
        profile.setMajor(SupplierData2.major);
        profile.setIntroduce(SupplierData2.introduce);
        profile.setLocation(SupplierData2.location);

        profiles.add(profile);
        profile = new Profile();
        profile.setId(SupplierData3.id);
        profile.setName(SupplierData3.name);
        profile.setMajor(SupplierData3.major);
        profile.setIntroduce(SupplierData3.introduce);
        profile.setLocation(SupplierData3.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData4.id);
        profile.setName(SupplierData4.name);
        profile.setMajor(SupplierData4.major);
        profile.setIntroduce(SupplierData4.introduce);
        profile.setLocation(SupplierData4.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData5.id);
        profile.setName(SupplierData5.name);
        profile.setMajor(SupplierData5.major);
        profile.setIntroduce(SupplierData5.introduce);
        profile.setLocation(SupplierData5.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData6.id);
        profile.setName(SupplierData6.name);
        profile.setMajor(SupplierData6.major);
        profile.setIntroduce(SupplierData6.introduce);
        profile.setLocation(SupplierData6.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData7.id);
        profile.setName(SupplierData7.name);
        profile.setMajor(SupplierData7.major);
        profile.setIntroduce(SupplierData7.introduce);
        profile.setLocation(SupplierData7.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData8.id);
        profile.setName(SupplierData8.name);
        profile.setMajor(SupplierData8.major);
        profile.setIntroduce(SupplierData8.introduce);
        profile.setLocation(SupplierData8.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData9.id);
        profile.setName(SupplierData9.name);
        profile.setMajor(SupplierData9.major);
        profile.setIntroduce(SupplierData9.introduce);
        profile.setLocation(SupplierData9.location);
        profiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData10.id);
        profile.setName(SupplierData10.name);
        profile.setMajor(SupplierData10.major);
        profile.setIntroduce(SupplierData10.introduce);
        profile.setLocation(SupplierData10.location);
        profiles.add(profile);


    }

    public Profile getProfile(int index) {
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
