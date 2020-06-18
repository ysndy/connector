package com.example.connector.jeongeun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

// 정보 수정
public class Edit_info extends AppCompatActivity {

    ImageView providerImg; // 이미지
    Button uploadImg, editOk; // 사진올리기, 수정완료
    EditText etProviderName, etProviderEmail, etProviderPnum, ProviderIntroduction; // 상호명, E-mail, 연락처, 업체소개

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);

        uploadImg = findViewById(R.id.uploadImg);
        editOk = findViewById(R.id.editOk);

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
