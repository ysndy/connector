package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class Find_ID_Pass extends AppCompatActivity {


    TextView findID, findPass;
    EditText findemail, findIdedit, findemailEdit;
    Button emailGo, emailGo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id_pass);

        findID= findViewById(R.id.findID);
        findemail= findViewById(R.id.findemail);
        findIdedit= findViewById(R.id.findIdEdit);
        findemailEdit= findViewById(R.id.findemailEdit);
        emailGo = findViewById(R.id.emailGo);
        emailGo2 = findViewById(R.id.emailGo2);

        Intent intent = getIntent();

        emailGo.setOnClickListener(new View.OnClickListener() { //아이디 찾기 > 이메일 전송
            @Override
            public void onClick(View v) {
                //아이디 찾는 이메일 전송 ?
            }
        });

        emailGo2.setOnClickListener(new View.OnClickListener() { //비밀번호 찾기 > 이메일 전송
            @Override
            public void onClick(View v) {
                //비밀번호 위한 이메일 Go?
            }
        });
    }
}