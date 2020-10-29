package com.example.connector.jeongeun.providerpage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

// 상품 수정 - 공급자 마이페이지 > 상품 리스트에 연결
public class Edit_item extends AppCompatActivity {

    Button upload_itemImg, deleteItem, editItem; // 사진올리기, 상품삭제, 수정
    ImageButton backBtn; // 뒤로가기
    EditText etItemName, etPrice, etOrigin; // 상품명, 단가, 원산지

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item);

        editItem = findViewById(R.id.editItem);
        backBtn = findViewById(R.id.backBtn);

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 수정 (임시)
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 수정
                Toast.makeText(Edit_item.this, "수정", Toast.LENGTH_SHORT).show();
            }
        });
    }
}