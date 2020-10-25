package com.example.connector.jeongeun;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.mainview.MainPageActivity;


// 외식업자 정보 (공급처 마이페이지 > 현황 > 신청/거래 현황 > 외식업자 정보)
public class Restaurant_info extends AppCompatActivity {

    ImageView proImg; // 외식업자 이미지
    Button callBtn, emailBtn; // 전화, E-mail
    ImageButton backBtn, homeBtn; // 뒤로가기, 홈
    TextView topResName, etinfo; // 탑패널 이름, 가게소개

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_info);

        proImg = findViewById(R.id.proImg);
        callBtn = findViewById(R.id.callBtn);
        emailBtn = findViewById(R.id.emailBtn);
        etinfo = findViewById(R.id.etinfo);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

        // 외식업자 이름 상단에 띄우기

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 홈
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Restaurant_info.this, MainPageActivity.class);
                startActivity(intent);
            }
        });

        // 전화
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Restaurant_info.this);
                dlg.setTitle("전화");
                dlg.setMessage("010 - 0000 - 0000");

                dlg.setPositiveButton("전화걸기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tel = "tel: 010-0000-0000";
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                    }
                });

                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = dlg.create();
                alertDialog.show();
            }
        });

        // 이메일 복사
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Restaurant_info.this);
                dlg.setTitle("이메일");
                dlg.setMessage("leod004@naver.com");

                dlg.setPositiveButton("이메일 복사", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("simple text", "leod0904@naver.com");
                        clipboard.setPrimaryClip(clip);
                    }
                });

                dlg.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = dlg.create();
                alertDialog.show();
            }
        });
    }
}
