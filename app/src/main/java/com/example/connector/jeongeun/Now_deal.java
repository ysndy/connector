package com.example.connector.jeongeun;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

// 공급자 마이페이지 현황 > 거래 현황
public class Now_deal extends Fragment {

    ListView now_deal_list;
    Button pr_now_dealBtn;
    ArrayList<Transaction> transactions;
    TextView pr_now_dealName, pr_now_dealDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_now_deal, container, false);

        now_deal_list = v.findViewById(R.id.now_deal_list);

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

        Now_deal_list now_deal_adapter = new Now_deal_list(transactions);
        now_deal_list.setAdapter(now_deal_adapter);

        return v;
    }
}
