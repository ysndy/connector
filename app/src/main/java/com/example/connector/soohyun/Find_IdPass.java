package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Find_IdPass extends AppCompatActivity {

    Button emailGo, emailGo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent intent = getIntent();

        emailGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디 찾는 이메일 전송 ?
            }
        });

        emailGo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //비밀번호 위한 이메일 Go?
            }
        });
    }
}
