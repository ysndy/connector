package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.product.ProductData2;
import com.example.connector.sampleData.supplierprofile.SupplierData2;

import java.util.ArrayList;

//좋아요 탭
public class LikeTab extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Product> products;
    private ListView likeItemListView;


    private String mParam1;
    private String mParam2;

    public LikeTab() {
        // Required empty public constructor
    }

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
        product1.setSupplyName(ProductData1.supply);
        products.add(product1);

        Product product2 = new Product();
        product2.setName(ProductData2.name);
        product2.setPrice(ProductData2.price);
        product2.setSupplyName(SupplierData2.name);
        products.add(product2);

        LikeTab_Adapter likeTab_adapter = new LikeTab_Adapter(products);
        likeItemListView.setAdapter(likeTab_adapter);

    }
}
