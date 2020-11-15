package com.example.connector.soohyun.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class SignupBtn extends AppCompatActivity {

    Button eSignup;
    ImageButton backBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupbtn);

        Intent intent = getIntent();

        eSignup = findViewById(R.id.eSignup);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        eSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });
    }
}
