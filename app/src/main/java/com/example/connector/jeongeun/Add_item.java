package com.example.connector.jeongeun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

// 상품 등록 화면 (공급처 마이페이지 > 상품 > 상품추가)
public class Add_item extends AppCompatActivity {

    ImageView providerImg; // 이미지
    Button uploadImg, addItem; // 사진등록, 상품등록
    TextView providerName, providerEmail, providerPnum; // 상품명, 단가, 원산지
    EditText etItemName, etPrice, etOrigin; // 상품명et, 단가et, 원산지et

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        uploadImg = findViewById(R.id.uploadImg);
        addItem = findViewById(R.id.addItem);

        // 사진 등록
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사진 등록
            }
        });

        // 상품 등록
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 받아와서 fragment_item_frag_je의 리스트뷰로 넘겨줌
                Toast.makeText(Add_item.this, "상품 등록", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
