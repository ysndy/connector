package com.example.connector.jeongeun;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.lang.reflect.Array;
import java.util.ArrayList;

// 공급자 마이페이지 현황 > 신청 현황
public class Now_request extends Fragment {

    Button pr_now_requestBtn;
    TextView pr_now_requestName, pr_now_requestDate;
    ListView now_request_list;
    ArrayList<Transaction> transactions;

    public Now_request() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_now_request, container, false);

        pr_now_requestBtn = v.findViewById(R.id.pr_now_requestBtn);
        now_request_list = v.findViewById(R.id.now_request_list);

        // 임시 데이터

        ArrayList<Transaction> transactions;
        transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();

        transaction1.setSupplyName(TransactionData1.supply);
        transaction1.setDate(TransactionData1.date);

        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();

        transaction2.setSupplyName(TransactionData2.supply);
        transaction2.setDate(TransactionData2.date);

        transactions.add(transaction2);

        Now_request_list now_request_adapter = new Now_request_list(transactions);
        now_request_list.setAdapter(now_request_adapter);

        return v;
    }
}
