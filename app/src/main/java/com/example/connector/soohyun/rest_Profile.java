package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class rest_Profile  extends AppCompatActivity {


    ImageView proImg;
    Button callBtn, emailBtn;
    TextView intro, intro_depth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_profile);

        proImg = findViewById(R.id.proImg);
        callBtn = findViewById(R.id.callBtn);
        emailBtn = findViewById(R.id.emailBtn);
        intro = findViewById(R.id.intro);
        intro_depth = findViewById(R.id.intro_depth);


        Intent intent = getIntent();

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(rest_Profile.this);
                dlg.setTitle("전화걸기");
                dlg.setMessage("010 - 0000 - 0000");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("전화", null); //전화 앱 접근
                dlg.show();
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(rest_Profile.this);
                dlg.setTitle("이메일 복사");
                dlg.setMessage("soohyun@mjc.ac.kr");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("복사하기", null); // 클립보드 저장기능
                dlg.show();
            }
        });

    }

}
