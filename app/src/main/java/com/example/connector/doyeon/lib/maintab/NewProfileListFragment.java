package com.example.connector.doyeon.lib.maintab;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.connector.R;
import com.example.connector.doyeon.activity.IntentName;
import com.example.connector.doyeon.activity.SupplierProfileActivity;
import com.example.connector.doyeon.lib.ProfileAdapter;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.sampleData.SupplierData1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewProfileListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewProfileListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Profile myProfile;
    ArrayList<Profile> newProfiles;
    ListView newListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewProfileListFragment(Profile myProfile) {
        // Required empty public constructor
        this.myProfile = myProfile;
    }

    public NewProfileListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewProfileListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewProfileListFragment newInstance(String param1, String param2) {
        NewProfileListFragment fragment = new NewProfileListFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_new_profile_list, container, false);
        newListView = rootView.findViewById(R.id.newListView);
        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(newListView.getContext(), "touch", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(newListView.getContext(), SupplierProfileActivity.class);
                intent.putExtra(IntentName.PROFILE_SUP, (Serializable) newProfiles.get(position));
                intent.putExtra(IntentName.PROFILE_RES, (Serializable) myProfile);
                startActivity(intent);
            }
        });
        setNewProfiles();
        return rootView;
    }

    public void setNewProfiles(){
        newProfiles = new ArrayList<>();
        for(int i=0; i<3; i++){

            Profile profile = new Profile();
            profile.setId(SupplierData1.id);
            profile.setName(SupplierData1.name);
            profile.setMajor(SupplierData1.major);
            newProfiles.add(profile);

        }

        ProfileAdapter adapter = new ProfileAdapter(newProfiles);
        newListView.setAdapter(adapter);
    }
}