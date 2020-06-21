package com.example.connector.jeongeun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.MainPageActivity;

// 정보 수정
public class Edit_info extends AppCompatActivity {

    ImageView providerImg; // 이미지
    Button uploadImg, editOk; // 사진올리기, 수정완료
    EditText etProviderName, etProviderEmail, etProviderPnum, ProviderIntroduction;// 상호명, E-mail, 연락처, 업체소개
    ImageButton backBtn, hometn; // 뒤로가기, 홈

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);

        uploadImg = findViewById(R.id.uploadImg);
        editOk = findViewById(R.id.editOk);
        backBtn = findViewById(R.id.backBtn);
        hometn = findViewById(R.id.homeBtn);

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 홈 (수정)
        hometn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_info.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

        // 사진 올리기
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사진 올리기
            }
        });

        // 수정 완료
        editOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Edit_info.this, "수정 완료", Toast.LENGTH_SHORT).show();
                // 데이터 받기
            }
        });


    }

}
