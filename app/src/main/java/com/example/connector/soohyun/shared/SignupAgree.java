package com.example.connector.soohyun.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class SignupAgree extends AppCompatActivity {

    TextView accessTerms, accessText;
    Button accessOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_agree);

        Intent intent = getIntent();

        accessTerms = findViewById(R.id.accessTerms);
        accessText = findViewById(R.id.accessText);
        accessOk = findViewById(R.id.accessOK);


        accessOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });

    }
}