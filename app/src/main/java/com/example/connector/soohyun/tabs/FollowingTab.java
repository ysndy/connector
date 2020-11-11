package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.product.ProductData2;
import com.example.connector.sampleData.supplierprofile.SupplierData1;
import com.example.connector.sampleData.supplierprofile.SupplierData4;
import com.example.connector.sampleData.supplierprofile.SupplierData5;

import java.util.ArrayList;

//팔로잉 탭
public class FollowingTab extends Fragment {

    //private ArrayList<Profile> profiles; 수정
    private ListView followingItemListView;

    private String mParam1;
    private String mParam2;

    public FollowingTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_re_following, container, false);

        followingItemListView = viewGroup.findViewById(R.id.followingItemListView);
        setProfileList();

        //공급업자 프로필 이동


        return followingItemListView;
    }

    private void setProfileList() {

        // 수정
        ArrayList<Profile> profiles;
        //
        profiles = new ArrayList<>();

        Profile profile1 = new Profile();
        profile1.setName(SupplierData4.name);
        profile1.setMajor(SupplierData4.major);
        profile1.setLocation(SupplierData4.location);
        profiles.add(profile1);

        Profile profile2 = new Profile();
        profile2.setName(SupplierData5.name);
        profile2.setMajor(SupplierData5.major);
        profile2.setLocation(SupplierData5.location);
        profiles.add(profile2);

        FollowingTab_Adapter followingTab_adapter = new FollowingTab_Adapter(profiles);
        followingItemListView.setAdapter(followingTab_adapter);


    }
}
