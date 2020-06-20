package com.example.connector.doyeon.lib.maintab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.activity.IntentName;
import com.example.connector.doyeon.activity.ProfileListActivity;
import com.example.connector.doyeon.activity.SupplierProfileActivity;
import com.example.connector.doyeon.lib.ProfileAdapter;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.SupplierData1;
import com.example.connector.sampleData.SupplierData2;
import com.example.connector.sampleData.SupplierData3;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendProfileListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendProfileListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Profile> recommendProfiles;
    ListView recommendListView;
    Profile myProfile;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecommendProfileListFragment() {
        // Required empty public constructor
    }
    public RecommendProfileListFragment(Profile myProfile) {
        this.myProfile = myProfile;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendProfileListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendProfileListFragment newInstance(String param1, String param2) {
        RecommendProfileListFragment fragment = new RecommendProfileListFragment();
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
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommend_profile_list, container, false);
        recommendListView = rootView.findViewById(R.id.recommendListView);
        View footer = getLayoutInflater().inflate(R.layout.listfooter_main_more, null, false);
        recommendListView.addFooterView(footer);
        Button moreBtn = (Button) footer.findViewById(R.id.moreBtn);
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileListActivity.class);
                startActivity(intent);
            }
        });
        recommendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(newListView.getContext(), "touch", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(recommendListView.getContext(), SupplierProfileActivity.class);
                intent.putExtra(IntentName.PROFILE_SUP, (Serializable) recommendProfiles.get(position));
                intent.putExtra(IntentName.PROFILE_RES, (Serializable) myProfile);
                startActivity(intent);
            }
        });
        setRecommendProfiles();
        return rootView;
    }

    public void setRecommendProfiles(){
        recommendProfiles = new ArrayList<>();

        Profile profile = new Profile();
        profile.setId(SupplierData1.id);
        profile.setName(SupplierData1.name);
        profile.setMajor(SupplierData1.major);
        profile.setRating(SupplierData1.rating);
        profile.setSimpleInfo(SupplierData1.simpleInfo);
        recommendProfiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData2.id);
        profile.setName(SupplierData2.name);
        profile.setMajor(SupplierData2.major);
        profile.setRating(SupplierData2.rating);
        profile.setSimpleInfo(SupplierData2.simpleInfo);
        recommendProfiles.add(profile);

        profile = new Profile();
        profile.setId(SupplierData3.id);
        profile.setName(SupplierData3.name);
        profile.setMajor(SupplierData3.major);
        profile.setRating(SupplierData3.rating);
        profile.setSimpleInfo(SupplierData3.simpleInfo);
        recommendProfiles.add(profile);

        ProfileAdapter adapter = new ProfileAdapter(recommendProfiles);
        recommendListView.setAdapter(adapter);
    }
}
