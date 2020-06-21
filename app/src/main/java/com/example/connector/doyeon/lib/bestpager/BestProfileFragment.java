package com.example.connector.doyeon.lib.bestpager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.activity.IntentName;
import com.example.connector.doyeon.activity.SupplierProfileActivity;
import com.example.connector.doyeon.lib.ProfileButton;
import com.example.connector.doyeon.objects.Profile;

import java.io.Serializable;

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
    private TextView profile_name, profile_info, profile_add;
    private Profile profileSup, profileRes;
    private ImageView profile_img;
    private LinearLayout layout;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_best_profile, container, false);
        profile_name = rootView.findViewById(R.id.bestProfileName);
        //profile_img = rootView.findViewById(R.id.bestProfileImageView);
        profile_add = rootView.findViewById(R.id.addressTV);
        profile_info = rootView.findViewById(R.id.infoTV);
        layout = rootView.findViewById(R.id.bestProfileObjectPanel);

        profile_name.setText(profileSup.getName());
        profile_info.setText(profileSup.getIntroduce());
        profile_add.setText(profileSup.getLocation());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_name.getContext(), SupplierProfileActivity.class);
                intent.putExtra(IntentName.PROFILE_RES, (Serializable)profileRes);
                intent.putExtra(IntentName.PROFILE_SUP, (Serializable)profileSup);
                startActivity(intent);
            }
        });

        //TODO:이미지 추가해야댐
        return rootView;
    }

}
