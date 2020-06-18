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

import java.util.zip.Inflater;

// 공급자 마이페이지 상품
public class Item_frag extends Fragment {

    Button add_itemBtn; // 상품추가 버튼
    ListView item_list; // 상품 리스트

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
