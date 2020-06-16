package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class RequestNow extends AppCompatActivity {

    TextView nowName, nowDate;
    ImageView nowproImg;
    Button nowBtn1, nowBtn2, nowBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_now);

        Intent intent = getIntent();


        nowBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(RequestNow.this);
                dlg.setTitle("수정요청");
                dlg.setMessage("수정요청하시겠습니까?");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

        nowBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(RequestNow.this);
                dlg.setTitle("수락");
                dlg.setMessage("수락하시겠습니까?");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

        nowBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(RequestNow.this);
                dlg.setTitle("거절");
                dlg.setMessage("거절하시겠습니까?");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });
    }
}
