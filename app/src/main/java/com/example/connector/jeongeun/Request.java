package com.example.connector.jeongeun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.MainPageActivity;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

// 신청현황
public class Request extends AppCompatActivity {

    TextView pcName, pcDate;
    ListView pc_lv;
    Button requestBtn, acceptBtn, refuseBtn;
    ImageButton backBtn, homeBtn;
    ArrayList<Transaction> transactions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.present_condition);

        pcName = findViewById(R.id.pcName);
        pcDate = findViewById(R.id.pcDate);
        pc_lv = findViewById(R.id.pc_lv);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 홈
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Request.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

        //임시데이터
        transactions = new ArrayList<>();

        ArrayList<Product> products;
        products = new ArrayList<>();
        Product product1 = new Product();

        product1.setName(ProductData1.name);
        product1.setPrice(ProductData1.price);

        products.add(product1);

        Transaction transaction1 = new Transaction();
        transaction1.setProducts(products);
        transaction1.setCount(TransactionData1.num);
        transaction1.setPriceTotal(TransactionData1.price);
        transaction1.setDate(TransactionData1.date);
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setProducts(products);
        transaction2.setCount(TransactionData2.num);
        transaction2.setPriceTotal(TransactionData2.price);
        transaction2.setDate(TransactionData2.date);
        transactions.add(transaction2);

        Request_adapter request_adapter = new Request_adapter(transactions);
        pc_lv.setAdapter(request_adapter);

        /*
        // 수정 요청
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 변경하신 내용으로 수정요청 하시겠습니까?
            }
        });

        // 수락
        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1) 수정된 정보가 있습니다. 수정 없이 기존 신청 내용으로 수락하시겠습니까?
                // 2) 신청을 수락하시겠습니까?
            }
        });

        // 거절
        refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 신청을 거절하시겠습니까?
            }
        });*/
    }
}
