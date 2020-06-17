package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayList<Product> selectedProducts;
    ListView productListView;
    Button requestBtn;
    TextView priceTotalTv, selectedCountTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestproductlist);

        inflating(); //리소스 인플레이팅
        setProductListView(); //리스트 세팅

        //이벤트처리
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCountTv.getText().toString().equals("0 개 상품 선택"))
                    Toast.makeText(getApplicationContext(), "상품, 수량을 선택해주세요", Toast.LENGTH_SHORT);
                else {
                    Intent intent = new Intent(getApplicationContext(), TosActivity.class);
                    intent.putExtra(IntentName.SELECTED_PRODUCTS, selectedProducts);
                    startActivity(intent);
                }
            }
        });

        productListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void inflating(){
        // 공급처, 외식업자 프로필 가져옴
        profile_sup = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_SUP);
        profile_res = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_RES);

        // 리소스 인플레이팅
        requestBtn = findViewById(R.id.requestBtn);
        productListView = findViewById(R.id.productListView);
        priceTotalTv = findViewById(R.id.priceTotalTv);
        selectedCountTv = findViewById(R.id.selectedCountTv);
        // 필드객체생성
        selectedProducts = new ArrayList<>();
    }

    private void setProductListView() {
        //리스트 세팅
        products = new ArrayList<>();
        profile_sup.insertProducts();
        products.addAll(profile_sup.getProducts());
        RequestAdapter adapter = new RequestAdapter(products, priceTotalTv, selectedCountTv);
        productListView.setAdapter(adapter);
    }
}
