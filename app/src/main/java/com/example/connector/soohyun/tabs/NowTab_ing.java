package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.transaction.TransactionData1;

import java.util.ArrayList;

//현황탭 중 거래현황탭
public class NowTab_ing extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Transaction> transactions;
    private ListView now2ItemListView;

    private String mParam1;
    private String mParam2;

    public NowTab_ing() {
        // Required empty public constructor
    }


      public static NowTab_ing newInstance(String param1, String param2) {
        NowTab_ing fragment = new NowTab_ing();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_now_tab_ing, container, false);

        now2ItemListView = viewGroup.findViewById(R.id.now2ItemListView);
        setTransactionList();
        return now2ItemListView;

    }

    private void setTransactionList() {

        transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setSupplyName(TransactionData1.supply);
        transaction1.setDate(TransactionData1.date);
        transactions.add(transaction1);

        NowTab_ing_Adapter nowTab_ing_adapter = new NowTab_ing_Adapter(transactions);
        now2ItemListView.setAdapter(nowTab_ing_adapter);

    }
}
