package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.ProductData1;
import com.example.connector.sampleData.ProductData2;
import com.example.connector.sampleData.SupplierData1;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoraeTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoraeTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Profile> profiles;
    private ListView goraeItemListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GoraeTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reGorae.
     */
    // TODO: Rename and change types and number of parameters
    public static GoraeTab newInstance(String param1, String param2) {
        GoraeTab fragment = new GoraeTab();
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_re_gorae, container, false);

        goraeItemListView = viewGroup.findViewById(R.id.goraeItemListView);
        setProfiletList();
        return goraeItemListView;
    }

    private void setProfiletList() {

        profiles = new ArrayList<>();

        Profile profile1 = new Profile();
        profile1.setName(SupplierData1.name);
        profile1.setMajor(SupplierData1.major);
        //profile1.getProducts().get(0).setPrice(SupplierData1.);
        profiles.add(profile1);

        GoraeTab_Adapter goraeTab_adapter = new GoraeTab_Adapter(profiles);
        goraeItemListView.setAdapter(goraeTab_adapter);

    }
}
