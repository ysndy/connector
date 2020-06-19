package com.example.connector.jeongeun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;


// 외식업자 정보 (공급처 마이페이지 > 현황 > 신청/거래 현황 > 외식업자 정보)
public class Restaurant_info extends AppCompatActivity {

    ImageView proImg; // 외식업자 이미지
    Button callBtn, emailBtn; // 전화, E-mail
    TextView tvinfo; // 가게소개
    EditText etinfo; // 가게소개et

    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        proImg = findViewById(R.id.proImg);
        callBtn = findViewById(R.id.callBtn);
        emailBtn = findViewById(R.id.emailBtn);
        tvinfo = findViewById(R.id.tvinfo);
        etinfo = findViewById(R.id.etinfo);

        // 전화
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 전화
            }
        });

        // 이메일 복사
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이메일 복사
            }
        });
    }
}
