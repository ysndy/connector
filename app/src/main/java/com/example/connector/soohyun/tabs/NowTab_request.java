package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.supplierprofile.SupplierData3;
import com.example.connector.sampleData.supplierprofile.SupplierData8;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

//현황탭 중 신청현황
public class NowTab_request extends Fragment {

    private ArrayList<Transaction> transactions;
    private ListView now1ItemListView;

    public NowTab_request() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_now_tab_request, container, false);

        now1ItemListView = viewGroup.findViewById(R.id.now1ItemListView);
        setTransactionList();
        return now1ItemListView;
    }

    private void setTransactionList() {
        transactions = new ArrayList<>();

        //임시데이터1
        Transaction transaction1 = new Transaction();
        transaction1.setProviderName(SupplierData3.name);
        transaction1.setDate(TransactionData1.date);
        transactions.add(transaction1);

        //임시데이터2
        Transaction transaction2 = new Transaction();
        transaction2.setProviderName(SupplierData8.name);
        transaction2.setDate(TransactionData2.date);
        transactions.add(transaction2);

        NowTab_request_Adapter nowTab_request_adapter = new NowTab_request_Adapter(transactions);
        now1ItemListView.setAdapter(nowTab_request_adapter);

    }
}
