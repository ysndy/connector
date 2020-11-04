package com.example.connector.jeongeun.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.lib.request.SupplierProductRequest;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.jeongeun.providerpage.Add_item;
import com.example.connector.sampleData.product.ProductData1;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// 공급자 마이페이지 상품
public class Item_frag extends Fragment {

    Button add_itemBtn; // 상품추가 버튼
    ListView item_list; // 상품 리스트
    Profile profile_sup; // 공급업자 프로필
    ArrayList<Transaction> transactions;

    public Item_frag(Profile profile_sup) {
        // Required empty public constructor
        this.profile_sup = profile_sup;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_frag_je, container, false);
        add_itemBtn = v.findViewById(R.id.add_ItemBtn);
        item_list = v.findViewById(R.id.item_list);
        setProducts();
        //profileSup

        // 임시 데이터
//
//        ArrayList<Product> products;
//        products = new ArrayList<>();
//        Product product1 = new Product();
//
//        product1.setName(ProductData1.name);
//        product1.setPrice(ProductData1.price);
//
//        products.add(product1);

        // 상품 추가
        add_itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // fragment는 this 객체 사용 X -> getActivity() 사용
               Intent intent = new Intent(getActivity(), Add_item.class);
               intent.putExtra(IntentName.PROFILE_SUP, profile_sup);
               startActivity(intent);
            }
        });


        return v;
    }

    private void setProducts() {
        // 서버에서 profileSup.getId 로 조회해서 상품들 가져옴
        //profileSup.insertProducts(getApplicationContext());
        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Product> products = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);
                    for (int i = 0; i < jResponse.length(); i++) {
                        JSONObject jso = jResponse.getJSONObject(i);

                        final Product product = new Product();
                        product.setCode(jso.getString(IntentName.CODE));
                        product.setCategory(jso.getString(IntentName.CATEGORY));
                        product.setFrom(jso.getString(IntentName.FROMTO));
                        product.setName(jso.getString(IntentName.NAME));
                        product.setPrice(jso.getInt(IntentName.PRICE));
                        product.setImageUrl(jso.getString(IntentName.IMG));
                        products.add(product);

                    }
                    profile_sup.setProducts(products);
                    setItemList(products);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);


                } catch (Exception e) {
                    Log.d("asd", "Profile.java - " + e.toString());
                }
            }
        };

        SupplierProductRequest supplierProductRequest = new SupplierProductRequest(profile_sup.getId(), rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(supplierProductRequest);
        //Log.d("asd", "Profile_products.size = "+products.size());
    }

    private void setItemList(ArrayList<Product> products){
        Item_list item_list_adapter = new Item_list(products);
        item_list.setAdapter(item_list_adapter);
    }


}
