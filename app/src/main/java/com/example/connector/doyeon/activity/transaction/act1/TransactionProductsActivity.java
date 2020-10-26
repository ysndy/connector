package com.example.connector.doyeon.activity.transaction.act1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.activity.transaction.act2.TransactionSetDateActivity;
import com.example.connector.doyeon.lib.RequestAdapter;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class TransactionProductsActivity extends Activity {

    Profile profile_sup, profile_res;
    ArrayList<Product> products;
    ArrayList<Product> selectedProducts;
    ListView productListView;
    Button requestBtn;
    TextView priceTotalTv, selectedCountTv;
    RequestAdapter adapter;

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
                if(adapter.getTotalPrice()==0)
                    Toast.makeText(getApplicationContext(), "상품, 수량을 선택해주세요", Toast.LENGTH_SHORT).show();
                else {
                    //거래 세팅 시작
                    Transaction transaction = new Transaction();
                    transaction.setSupplier(profile_sup); //해당 거래의 공급자 세팅
                    transaction.setSupplierID(profile_sup.getId());
                    transaction.setRestaurant(profile_res); //해당 거래의 외식업자 세팅
                    transaction.setRestaurantID(profile_res.getId());
                    transaction.setPriceTotal(Integer.parseInt(priceTotalTv.getText().toString())); // 거래 총 금액

                    setSelectedProducts(products, selectedProducts);
                    //선택된 상품 담기
                    //selectedProducts = adapter.getSelectedProducts();
                    Log.d("asd", selectedProducts.size()+" "+transaction.getPriceTotal());
                    Log.d("asd", "Code: "+selectedProducts.get(0).getCode()+" "+transaction.getPriceTotal());
                    Log.d("asd", "Code: "+products.get(0).getCode()+" "+transaction.getPriceTotal());

                    //여기부터
                    Intent intent = new Intent(getApplicationContext(), TransactionSetDateActivity.class);
                    intent.putParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS, selectedProducts);
                    intent.putExtra(IntentName.TRANSACTION, transaction);
                    //intent.putExtra(IntentName.PROFILE_SUP, profile_sup);
                    //intent.putExtra(IntentName.PROFILE_RES, profile_res);
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

    public void setSelectedProducts(ArrayList<Product> products, ArrayList<Product> products_selected){
        products_selected.clear();
        for(int i =0;i<products.size();i++) {
            if(products.get(i).getSelectedCount()>0) products_selected.add(products.get(i));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void inflating(){
        // 공급처, 외식업자 프로필 가져옴
        profile_sup = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_SUP);
        profile_res = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_RES);
        products = getIntent().getParcelableArrayListExtra(IntentName.PRODUCTS);

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
        //products = new ArrayList<>();
        //profile_sup.insertProducts(getApplicationContext());
        //products.addAll(profile_sup.getProducts());
        adapter = new RequestAdapter(products, priceTotalTv, selectedCountTv);
        productListView.setAdapter(adapter);
    }
}
