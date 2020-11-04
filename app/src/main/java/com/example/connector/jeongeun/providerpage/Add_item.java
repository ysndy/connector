package com.example.connector.jeongeun.providerpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.activity.mainview.MainPageActivity;
import com.example.connector.doyeon.activity.transaction.act4.TransactionRequestActivity;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.lib.request.InsertProductRequest;
import com.example.connector.doyeon.lib.request.InsertTransactionsRequest;
import com.example.connector.doyeon.objects.Profile;

// 상품 등록 화면 (공급처 마이페이지 > 상품 > 상품추가)
public class Add_item extends AppCompatActivity {

    ImageView providerImg; // 이미지
    Button uploadImg, addItem; // 사진등록, 상품등록
    ImageButton backBtn; // 뒤로가기
    EditText etItemName, etPrice, etOrigin; // 상품명et, 단가et, 원산지et
    Profile profile_sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);
        profile_sup = getIntent().getParcelableExtra(IntentName.PROFILE_SUP);

        uploadImg = findViewById(R.id.uploadImg);
        addItem = findViewById(R.id.addItem);
        backBtn = findViewById(R.id.backBtn);
        etItemName = findViewById(R.id.etItemName);
        etPrice = findViewById(R.id.etPrice);
        etOrigin = findViewById(R.id.etOrigin);

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 사진 등록
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사진 등록
            }
        });

        // 상품 등록 (임시)
        addItem.setOnClickListener(new View.OnClickListener() {

            String code = profile_sup.getId()+"_"+profile_sup.getProducts().size();
            String supplier = profile_sup.getId();
            String price = etPrice.getText().toString();
            String name = etItemName.getText().toString();
            String fromto = etOrigin.getText().toString();

            @Override
            public void onClick(View v) {
                // EditText에서 받아와서 fragment_item_frag_je의 리스트뷰로 넘겨줌
                Toast.makeText(Add_item.this, "상품 등록", Toast.LENGTH_SHORT).show();
                Response.Listener rListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                        } catch (Exception e) {
                            Log.d("asd", "Request.java - " + e.toString());
                        }
                    }
                };
                InsertProductRequest insertProductRequest = new InsertProductRequest(code, supplier, price, name, fromto, rListener); //Request 처리 클래스
                RequestQueue queue = Volley.newRequestQueue(Add_item.this);
                queue.add(insertProductRequest);

            }
        });
    }

}
