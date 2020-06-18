package com.example.connector.doyeon.lib.profiletab;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.lib.ProductAdapter;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.sampleData.ProductData1;
import com.example.connector.doyeon.sampleData.ProductData2;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Product> products;
    private ListView productListView;
    private Profile profile;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public ProductListFragment(Profile profile) {
        this.profile = profile;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(String param1, String param2) {
        ProductListFragment fragment = new ProductListFragment();
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_product_list, container, false);
        productListView = rootView.findViewById(R.id.productListView);
        setProductList();
        return rootView;
    }

    //TODO: profile.getID()로 서버에서 데이터 불러옴
    private void setProductList() {

        products = new ArrayList<>();

        Product product = new Product();
        product.setName(ProductData1.name);
        product.setPrice(ProductData1.price);
        products.add(product);

        Product product2 = new Product();
        product2.setName(ProductData2.name);
        product2.setPrice(ProductData2.price);
        products.add(product2);

        ProductAdapter adapter = new ProductAdapter(products);
        productListView.setAdapter(adapter);

    }
}
