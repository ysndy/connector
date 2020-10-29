package com.example.connector.doyeon.activity.transaction.act4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.activity.mainview.MainPageActivity;
import com.example.connector.doyeon.lib.request.InsertTransactionRequest;
import com.example.connector.doyeon.activity.transaction.act4.transaction.TransactionApplicationForm;
import com.example.connector.doyeon.lib.request.InsertTransactionsRequest;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;

import org.json.JSONObject;

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
        Log.d("asd", transactionProducts.size() + "");

        //프래그먼트 생성
        TransactionApplicationForm applicationForm = new TransactionApplicationForm(transactionProducts, transaction);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.applicationLayout, applicationForm).commitAllowingStateLoss();

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkBox.isChecked()) {
                    //서버에 거래 데이터 저장

                    //인설트문 제작
                    String query = "INSERT INTO transaction (supplierID, restaurantID, date, state, productCode, count) VALUES";
                    for (int i = 0; i < transactionProducts.size(); i++) {

                        String[] field = new String[6];
                        field[0] = transaction.getSupplier().getId();
                        field[1] = transaction.getRestaurant().getId();
                        field[2] = transaction.getDate();
                        field[3] = "거래예정";
                        field[4] = transactionProducts.get(i).getCode();
                        field[5] = transactionProducts.get(i).getSelectedCount() + "";

                        String query_in = "(";
                        for (int j = 0; j < field.length; j++) {
                            query_in += "'" + field[j] + "'";
                            if (j != field.length - 1) query_in += ",";
                        }
                        query_in += ")";

                        if (i != transactionProducts.size() - 1) query_in += ",";
                        query += query_in;

                    }
                    Log.d("asd", "Ts Ist query = " + query);

                    Response.Listener rListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Toast.makeText(getApplicationContext(), "거래일정이 생성되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } catch (Exception e) {
                                Log.d("asd", "Request.java - " + e.toString());
                            }
                        }
                    };
                    InsertTransactionsRequest insertTransactionsRequest = new InsertTransactionsRequest(query, rListener); //Request 처리 클래스
                    RequestQueue queue = Volley.newRequestQueue(TransactionRequestActivity.this);
                    queue.add(insertTransactionsRequest);

                } else
                    Toast.makeText(getApplicationContext(), "신청내역 확인여부를 체크해주세요", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
