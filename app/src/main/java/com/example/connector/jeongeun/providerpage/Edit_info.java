package com.example.connector.jeongeun.providerpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.mainview.MainPageActivity;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.objects.Profile;

// 정보 수정
public class Edit_info extends AppCompatActivity {

    ImageView providerImg; // 이미지
    Button uploadImg, editOk; // 사진올리기, 수정완료
    EditText etProviderName, etProviderEmail, etProviderPnum, etProviderAddress ,providerIntroduction;// 상호명, E-mail, 연락처, 업체소개
    ImageButton backBtn, hometn; // 뒤로가기, 홈
    Spinner spinCategory; // 카테고리 스피너
    Profile profile_sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        profile_sup = getIntent().getParcelableExtra(IntentName.PROFILE_SUP);

        uploadImg = findViewById(R.id.uploadImg);
        editOk = findViewById(R.id.editOk);
        backBtn = findViewById(R.id.backBtn);
        hometn = findViewById(R.id.homeBtn);

        spinCategory = findViewById(R.id.spinCategory); //카테고리 스피너
        etProviderName = findViewById(R.id.etProviderName);
        etProviderEmail = findViewById(R.id.etProviderEmail);
        etProviderPnum = findViewById(R.id.etProviderPnum);
        etProviderAddress = findViewById(R.id.etProviderAddress);
        providerIntroduction = findViewById(R.id.ProviderIntroduction);

        etProviderName.setText(profile_sup.getName());
        etProviderEmail.setText(profile_sup.getEmail());
        etProviderPnum.setText(profile_sup.getCallNumber());
        etProviderAddress.setText(profile_sup.getLocation());
        providerIntroduction.setText(profile_sup.getIntroduce());

        final String[] spinCate = {"분류", "신선식품", "가루", "일회용품", "유제품"}; // 카테고리 목록 배열

        ArrayAdapter<String> adapter; //카테고리
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinCate);
        spinCategory.setAdapter(adapter);

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
                // 서버 업데이트
            }
        });


    }

}
