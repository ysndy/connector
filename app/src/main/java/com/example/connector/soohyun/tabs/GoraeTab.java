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
import com.example.connector.jeongeun.tabs.Provider_list;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.restaurantprofile.RestaurantData1;
import com.example.connector.sampleData.supplierprofile.SupplierData1;

import java.util.ArrayList;

//거래처탭
public class GoraeTab extends Fragment {

    private ArrayList<Profile> profiles;
    private ListView goraeItemListView;

    public GoraeTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_re_gorae, container, false);

        goraeItemListView = viewGroup.findViewById(R.id.goraeItemListView);
        setProfileList();

        return viewGroup; // viewGroup을 리턴해야댐
    }

    private void setProfileList() {

        ArrayList<Profile> profiles; //정은수정
        profiles = new ArrayList<>();


        ArrayList<Product> products = new ArrayList<>(); // 프로필에서 가지고 있는 상품들
        Product product1 = new Product(); // 상품1
        product1.setPrice(5000); //상품의 가격
        products.add(product1); //상품들에 상품 추가

        Profile profile1 = new Profile();
        profile1.setName(SupplierData1.name);
        profile1.setMajor(SupplierData1.major);
        profile1.setCallNumber(SupplierData1.callNumber); //전화번호 추가
        profile1.setProducts(products);

        profile1.getProducts().get(0).setPrice(ProductData1.price);
        profiles.add(profile1);

        GoraeTab_Adapter goraeTab_adapter = new GoraeTab_Adapter(profiles);
        goraeItemListView.setAdapter(goraeTab_adapter);

    }
}
