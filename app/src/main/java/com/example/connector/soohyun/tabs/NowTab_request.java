package com.example.connector.soohyun.tabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

//현황탭 중 신청현황
public class NowTab_request extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Transaction> transactions;
    private ListView now1ItemListView;

    private String mParam1;
    private String mParam2;

    private Button greenBtn; //거래진행중 버튼


    public NowTab_request() {
        // Required empty public constructor
    }


     public static NowTab_request newInstance(String param1, String param2) {
        NowTab_request fragment = new NowTab_request();
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_now_tab_request, container, false);

        now1ItemListView = viewGroup.findViewById(R.id.now1ItemListView);
        setTransactionList();
        return now1ItemListView;
    }

    private void setTransactionList() {
        transactions = new ArrayList<>();

        Transaction transaction1 = new Transaction();
        transaction1.setSupplyName(TransactionData1.supply);
        transaction1.setDate(TransactionData1.date);
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setSupplyName(TransactionData2.supply);
        transaction2.setDate(TransactionData2.date);
        transactions.add(transaction2);

        NowTab_request_Adapter nowTab_request_adapter = new NowTab_request_Adapter(transactions);
        now1ItemListView.setAdapter(nowTab_request_adapter);

    }
}
