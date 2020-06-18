package com.example.connector.soohyun.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.MainPageActivity;
import com.example.connector.soohyun.restaurantpage.EditRequest;
import com.example.connector.soohyun.restaurantpage.NowRequest;

public class Login extends AppCompatActivity {


    ImageView login_logoImg;
    EditText ID, Password;
    Button loginBtn, fjoin, fid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_logoImg = findViewById(R.id.login_logoImg);
        ID = findViewById(R.id.ID);
        Password = findViewById(R.id.Password);
        loginBtn = findViewById(R.id.loginBtn);
        fjoin = findViewById(R.id.fjoin);
        fid = findViewById(R.id.fid);

        Intent intent = getIntent();

        fjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NowRequest.class);
                startActivity(intent);
            }
        });

        fid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Find_ID_Pass.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });

    }

}
