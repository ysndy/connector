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

public class rest_EditRequest extends AppCompatActivity {

    TextView reqName, reqDate, reqItemName, reqItemAE, reqItemPrice, reqItemDate;
    ImageView reqproImg, reqItemImg;
    Button reqBtn1, reqBtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res_editrequest);

        Intent intent = getIntent();

        reqBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(rest_EditRequest.this);
                dlg.setTitle("신청요청-거절");
                dlg.setMessage("거절하시겠습니까?");
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

        reqBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(rest_EditRequest.this);
                dlg.setTitle("신청요청 - 수락");
                dlg.setMessage("수락하시겠습니까?");
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });

    }

}
