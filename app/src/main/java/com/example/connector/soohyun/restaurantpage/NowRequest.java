package com.example.connector.soohyun.restaurantpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

//신청현황
public class NowRequest extends AppCompatActivity {

    TextView nowName, nowDate, nowNum;
    ImageView nowproImg;
    ListView listView_now;
    ArrayList<Transaction> transactions;
    ImageButton backBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_nowrequest);

        Intent intent = getIntent();

        nowName = findViewById(R.id.nowName);
        nowDate = findViewById(R.id.joinId);
        nowNum = findViewById(R.id.nowNum);
        nowproImg = findViewById(R.id.nowproImg);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        //transaction1.setProducts(products);
        transaction1.setCount(TransactionData1.num);
        transaction1.setPriceTotal(TransactionData1.price);
        transaction1.setDate(TransactionData1.date);
        transactions.add(transaction1);

        Transaction transaction2 = new Transaction();
        //transaction2.setProducts(products);
        transaction2.setCount(TransactionData2.num);
        transaction2.setPriceTotal(TransactionData2.price);
        transaction2.setDate(TransactionData2.date);
        transactions.add(transaction2);

        listView_now = findViewById(R.id.now_listview);

        InfoListAdapter infoListAdapter = new InfoListAdapter(transactions);
        listView_now.setAdapter(infoListAdapter);

    }

}
