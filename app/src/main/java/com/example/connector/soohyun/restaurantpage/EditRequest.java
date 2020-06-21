package com.example.connector.soohyun.restaurantpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;

//공급자로부터 받아온 수정요청 페이지
public class EditRequest extends AppCompatActivity {

    TextView reqName, reqDate, reqItemName, reqItemAE, reqItemPrice, reqItemDate;
    ImageView reqproImg, reqItemImg;
    Button reqBtn1, reqBtn2;
    ListView listView_edit;
    ArrayList<Transaction> transactions;
    ImageButton backBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_editrequest);

        Intent intent = getIntent();

        reqBtn1 = findViewById(R.id.reqBtn1);
        reqBtn2 = findViewById(R.id.reqBtn2);
        listView_edit = findViewById(R.id.edit_listview);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

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

        InfoListAdapter infoListAdapter = new InfoListAdapter(transactions);
        listView_edit.setAdapter(infoListAdapter);

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


        reqBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(EditRequest.this);
                dlg.setTitle("신청요청-거절");
                dlg.setMessage("거절하시겠습니까?");
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

        reqBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(EditRequest.this);
                dlg.setTitle("신청요청 - 수락");
                dlg.setMessage("수락하시겠습니까?");
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

    }

}
