package com.example.connector.soohyun.restaurantpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.ProductData1;
import com.example.connector.sampleData.TransactionData1;
import com.example.connector.sampleData.TransactionData2;

import java.util.ArrayList;

public class NowRequest extends AppCompatActivity {

    TextView nowName, nowDate;
    ImageView nowproImg;
    ListView listView_now;
    ArrayList<Transaction> transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_nowrequest);

        Intent intent = getIntent();

        nowName = findViewById(R.id.nowName);
        nowDate = findViewById(R.id.joinId);
        nowproImg = findViewById(R.id.nowproImg);

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

        listView_now = findViewById(R.id.now_listview);

        InfoListAdapter infoListAdapter = new InfoListAdapter(transactions);
        listView_now.setAdapter(infoListAdapter);

    }

}
