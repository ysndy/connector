package com.example.connector.jeongeun;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.product.ProductData1;

import java.util.ArrayList;

// 공급자 마이페이지 상품
public class Item_frag extends Fragment {

    Button add_itemBtn; // 상품추가 버튼
    ListView item_list; // 상품 리스트
    ArrayList<Transaction> transactions;

    public Item_frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_frag_je, container, false);

        add_itemBtn = v.findViewById(R.id.add_ItemBtn);
        item_list = v.findViewById(R.id.item_list);

        // 임시 데이터

        ArrayList<Product> products;
        products = new ArrayList<>();
        Product product1 = new Product();

        product1.setName(ProductData1.name);
        product1.setPrice(ProductData1.price);

        products.add(product1);


        Item_list item_list_adapter = new Item_list(products);
        item_list.setAdapter(item_list_adapter);

        // 상품 추가
        add_itemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // fragment는 this 객체 사용 X -> getActivity() 사용
               Intent intent = new Intent(getActivity(), Add_item.class);
               startActivity(intent);
            }
        });


        return v;
    }
}
