package com.example.connector.soohyun.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.MainPageActivity;
import com.example.connector.soohyun.restaurantpage.EditRequest;
import com.example.connector.soohyun.restaurantpage.MyPage;
import com.example.connector.soohyun.restaurantpage.NowRequest;
import com.example.connector.soohyun.restaurantpage.RestaurantProfileActivity;

public class Login extends AppCompatActivity {


    ImageView login_logoImg;
    EditText ID, Password;
    Button login1Btn, login2Btn, fjoin, fid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_logoImg = findViewById(R.id.login_logoImg);
        ID = findViewById(R.id.ID);
        Password = findViewById(R.id.Password);
        login1Btn = findViewById(R.id.login1Btn);
        login2Btn = findViewById(R.id.login2Btn);
        fjoin = findViewById(R.id.fjoin);
        fid = findViewById(R.id.fid);

        //입력된 아이디로 서버에서 조회한 뒤 PW 맞추어보고 틀리면 빠꾸 맞으면 로그인


        Intent intent = getIntent();

        fjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
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

        login1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });

        login2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);
            }
        });
    }

}
