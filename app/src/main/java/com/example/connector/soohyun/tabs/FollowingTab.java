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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Profile> profiles;
    private ListView followingItemListView;

    private String mParam1;
    private String mParam2;

    public FollowingTab() {
        // Required empty public constructor
    }


     public static FollowingTab newInstance(String param1, String param2) {
        FollowingTab fragment = new FollowingTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_re_following, container, false);

        followingItemListView = viewGroup.findViewById(R.id.followingItemListView);
        setProfileList();
        return followingItemListView;
    }

    private void setProfileList() {

        profiles = new ArrayList<>();

        Profile profile1 = new Profile();
        profile1.setName(SupplierData4.name);
        profile1.setMajor(ProductData1.category);
        profile1.setLocation(SupplierData1.location);
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
