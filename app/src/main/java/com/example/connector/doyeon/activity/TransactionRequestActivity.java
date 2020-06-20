package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.connector.R;
import com.example.connector.doyeon.lib.transaction.TransactionApplicationForm;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class TransactionRequestActivity extends AppCompatActivity {

    //LinearLayout layout;
    //1. 인텐트 받음(트랜잭션, 선택상품리스트) 2. 거래신청서 프래그먼트 보여줌 3. 거래데이터 서버에 저장
    Transaction transaction;
    ArrayList<Product> transactionProducts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_request);
        //layout = findViewById(R.id.applicationLayout);
        //인텐트
        Intent intent = getIntent();
        transactionProducts = intent.getParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS);
        transaction = intent.getParcelableExtra(IntentName.TRANSACTION);

        //프래그먼트 생성
        TransactionApplicationForm applicationForm = new TransactionApplicationForm(transactionProducts, transaction);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.applicationLayout, applicationForm).commitAllowingStateLoss();

    }
}
