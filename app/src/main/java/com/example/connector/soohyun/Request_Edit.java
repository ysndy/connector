package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Request_Edit extends AppCompatActivity {

    TextView reName, reDate;
    ImageView reproImg;
    Button reBtn1, reBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_edit);

        Intent intent = getIntent();


        reBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(Request_Edit.this);
                dlg.setTitle("신청요청-거절");
                dlg.setMessage("거절하시겠습니까?");
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

        reBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(Request_Edit.this);
                dlg.setTitle("신청요청 - 수락");
               dlg.setMessage("수락하시겠습니까?");
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

    }
}
