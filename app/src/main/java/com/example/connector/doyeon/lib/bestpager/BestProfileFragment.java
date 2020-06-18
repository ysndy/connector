package com.example.connector.doyeon.lib.bestpager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BestProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BestProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView profile_name;
    private Profile profileSup, profileRes;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BestProfileFragment() {
        // Required empty public constructor
    }
    public BestProfileFragment(Profile profileSup, Profile profileRes) {
        this.profileSup = profileSup;
        this.profileRes = profileRes;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BestProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BestProfileFragment newInstance(String param1, String param2) {
        BestProfileFragment fragment = new BestProfileFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_best_profile, container, false);
        profile_name = rootView.findViewById(R.id.bestProfileName);
        profile_name.setText(profileSup.getName());
        return rootView;
    }
}
