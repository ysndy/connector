package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.lib.ProductAdapter;
import com.example.connector.doyeon.lib.RequestAdapter;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.sampleData.ProductData1;

import java.util.ArrayList;

public class TransactionProductsActivity extends Activity {

    Profile profile_sup, profile_res;
    ArrayList<Product> products;
    ListView productListView;
    Button requestBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestproductlist);

        // 공급처, 외식업자 프로필 가져옴
        profile_sup = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_SUP);
        profile_res = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_RES);


    }

    private void inflating(){
        requestBtn = findViewById(R.id.requestBtn);
        productListView = findViewById(R.id.productListView);
    }

    private void setProductListView() {
        //리스트 세팅

        products = new ArrayList<>();
        profile_sup.insertProducts();
        products.addAll(profile_sup.getProducts());
        RequestAdapter adapter = new RequestAdapter(products);
        productListView.setAdapter(adapter);
    }
}
