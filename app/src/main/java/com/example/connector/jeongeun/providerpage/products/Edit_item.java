package com.example.connector.jeongeun.providerpage.products;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.dictionary.IntentName;
import com.example.connector.doyeon.dictionary.Table_product;
import com.example.connector.doyeon.lib.request.InsertTransactionsRequest;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.sql.DySQL_Delete;
import com.example.connector.doyeon.sql.DySQL_Update;
import com.example.connector.doyeon.util.Util;

// 상품 수정 - 공급자 마이페이지 > 상품 리스트에 연결
public class Edit_item extends AppCompatActivity {

    Button upload_itemImg, deleteItem, editItem; // 사진올리기, 상품삭제, 수정
    ImageButton backBtn; // 뒤로가기
    EditText etItemName, etPrice, etOrigin; // 상품명, 단가, 원산지
    Product product;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item);

        deleteItem = findViewById(R.id.deleteItem);
        editItem = findViewById(R.id.editItem);
        backBtn = findViewById(R.id.backBtn);
        product = getIntent().getParcelableExtra(IntentName.PRODUCTS);

        etItemName = findViewById(R.id.etItemName);
        etPrice = findViewById(R.id.etPrice);
        etOrigin = findViewById(R.id.etOrigin);

        etItemName.setText(product.getName());
        etPrice.setText(product.getPrice()+"");
        etOrigin.setText(product.getFrom());

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DySQL_Delete sql_delete = new DySQL_Delete();
                sql_delete.setTableName(Table_product.TABLE_NAME);
                sql_delete.setWhere(Table_product.CODE+" = '"+product.getCode()+"'");
                String query = sql_delete.getQuery();

                Response.Listener rListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(Edit_item.this, product.getName()+" 상품이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (Exception e) {
                            Util.printingLog(Edit_item.this.getClass().getSimpleName(), e.toString());
                        }
                    }
                };
                InsertTransactionsRequest insertTransactionsRequest = new InsertTransactionsRequest(query, rListener); //Request 처리 클래스
                RequestQueue queue = Volley.newRequestQueue(Edit_item.this);
                queue.add(insertTransactionsRequest);

            }
        });

        // 수정 (임시)
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수정

                String name = etItemName.getText().toString();
                String price = etPrice.getText().toString();
                String fromTo = etOrigin.getText().toString();

                DySQL_Update sql = new DySQL_Update();
                sql.setTableName("product");
                String[][] data = {
                        {"name", name}
                        ,{"price", price}
                        ,{"fromto", fromTo}
                };
                sql.setfAndValues(data);
                sql.setWhere("code = '"+product.getCode()+"'");
                String query = sql.getQuery();

                Response.Listener rListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Toast.makeText(Edit_item.this, product.getName()+" 상품이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        } catch (Exception e) {
                            Util.printingLog(Edit_item.this.getClass().getSimpleName(), e.toString());
                            //Log.d("asd", "Request.java - " + e.toString());
                        }
                    }
                };
                InsertTransactionsRequest insertTransactionsRequest = new InsertTransactionsRequest(query, rListener); //Request 처리 클래스
                RequestQueue queue = Volley.newRequestQueue(Edit_item.this);
                queue.add(insertTransactionsRequest);

            }

        });
    }
}