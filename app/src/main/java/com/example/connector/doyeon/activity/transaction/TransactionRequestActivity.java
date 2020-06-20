package com.example.connector.doyeon.activity.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.connector.R;
import com.example.connector.doyeon.activity.IntentName;
import com.example.connector.doyeon.activity.MainPageActivity;
import com.example.connector.doyeon.lib.transaction.TransactionApplicationForm;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class TransactionRequestActivity extends AppCompatActivity {

    //LinearLayout layout;
    //1. 인텐트 받음(트랜잭션, 선택상품리스트) 2. 거래신청서 프래그먼트 보여줌 3. 거래데이터 서버에 저장
    Transaction transaction;
    ArrayList<Product> transactionProducts;
    Button completeBtn;
    CheckBox checkBox;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_request);
        completeBtn = findViewById(R.id.completeBtn);
        checkBox = findViewById(R.id.check);

        //인텐트
        Intent intent = getIntent();
        transactionProducts = intent.getParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS);
        transaction = intent.getParcelableExtra(IntentName.TRANSACTION);
        Log.d("asd", transactionProducts.size()+"");
        
        //프래그먼트 생성
        TransactionApplicationForm applicationForm = new TransactionApplicationForm(transactionProducts, transaction);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.applicationLayout, applicationForm).commitAllowingStateLoss();

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBox.isChecked()) {
                    //서버에 거래 데이터 저장
                    Toast.makeText(getApplicationContext(), "거래일정이 생성되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else Toast.makeText(getApplicationContext(), "신청내역 확인여부를 체크해주세요", Toast.LENGTH_SHORT).show();
            }});

    }
}
