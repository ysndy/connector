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
import com.example.connector.doyeon.activity.mainview.MainPageActivity;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

// 거래 현황
public class Deal extends AppCompatActivity {

    TextView dpcName, dpcDate, dpcNumtv;
    ListView dpc_lv;
    Button refuseBtn;
    ImageButton backBtn, homeBtn;
    ArrayList<Transaction> transactions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deal_present_condition);

        dpcName = findViewById(R.id.dpcName);
        dpcDate = findViewById(R.id.dpcDate);
        dpcNumtv = findViewById(R.id.dpcNumtv);
        dpc_lv = findViewById(R.id.dpc_lv);
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
                Intent intent = new Intent(Deal.this, MainPageActivity.class);
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

        Deal_adapter deal_adapter = new Deal_adapter(transactions);
        dpc_lv.setAdapter(deal_adapter);

        /*
        // 철회
        refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 정말로 철회요청을 하시겠습니까?
            }
        }); */

    }
}
