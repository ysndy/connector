package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.lib.RequestAdapter;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class TransactionProductsActivity extends Activity {

    Profile profile_sup, profile_res;
    ArrayList<Product> products;
    ListView productListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestproductlist);

        profile_sup = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_SUP);
        profile_res = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_RES);

        productListView = findViewById(R.id.productListView);

        products = new ArrayList<>();
        products.addAll(profile_sup.getProducts());
        RequestAdapter adapter = new RequestAdapter(products);
        productListView.setAdapter(adapter);

    }
}
