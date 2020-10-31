package com.example.connector.doyeon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.connector.R;

// 필터 > 분류 리스트 기능 구현 (분류 데이터 가져오기)
public class Filter_category extends Fragment {

    private ListView category_list;
    private ArrayAdapter<String> arrayAdapter;

    public Filter_category() {

    }

    public ListView getCategory_list(){
        return category_list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_category, container, false);

        category_list = v.findViewById(R.id.category_list);
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.category_list));

        category_list.setAdapter(arrayAdapter);
        //category_list.getSelectedItem().toString();
        /* 카테고리 클릭
        category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }); */

        return v;
    }
}