package com.example.connector.doyeon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.connector.R;

// 필터 > 분류 리스트 기능 구현 (분류 데이터 가져오기)
public class Filter_category extends Fragment {

    public Filter_category() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_category, container, false);

        return v;
    }
}