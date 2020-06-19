package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.lib.ProductAdapter;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.sampleData.ProductData1;
import com.example.connector.sampleData.ProductData2;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikeTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikeTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Product> products;
    private ListView likeItemListView;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LikeTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reLike.
     */
    // TODO: Rename and change types and number of parameters
    public static LikeTab newInstance(String param1, String param2) {
        LikeTab fragment = new LikeTab();
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_re_like, container, false);

        likeItemListView = viewGroup.findViewById(R.id.likeItemListView);
        setProductList();
        return likeItemListView;
    }

    private void setProductList() {

        products = new ArrayList<>();

        Product product1 = new Product();
        product1.setName(ProductData1.name);
        product1.setPrice(ProductData1.price);
        product1.setFrom(ProductData1.supply);
        products.add(product1);

        Product product2 = new Product();
        product2.setName(ProductData2.name);
        product2.setPrice(ProductData2.price);
        product2.setFrom(ProductData2.supply);
        products.add(product2);

        LikeTab_Adapter likeTab_adapter = new LikeTab_Adapter(products);
        likeItemListView.setAdapter(likeTab_adapter);

    }
}
