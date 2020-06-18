package com.example.connector.soohyun;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class Signup extends AppCompatActivity {

    EditText joinId, jpinPass, joinEmail, joinNum;
    TextView joinAgree;
    Button goBtn, emailver;
    CheckBox checkAgree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        goBtn = findViewById(R.id.goBtn);
        joinId = findViewById(R.id.joinId);
        jpinPass = findViewById(R.id.joinPass);
        joinEmail = findViewById(R.id.joinEmail);
        joinNum = findViewById(R.id.joinNum);
        joinAgree = findViewById(R.id.goBtn);
        checkAgree = findViewById(R.id.checkAgree);
        emailver = findViewById(R.id.email_ver);

        Intent intent = getIntent();

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
                dlg.setTitle("회원가입 완료");
                dlg.setMessage("축하합니다.\nGo래처 회원이 되었습니다.\n로그인해주세요. ");
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dlg.show();
            }
        });

        emailver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
                dlg.setTitle("이메일 인증");
                dlg.setMessage("메일함을 확인하여 인증해주세요.");
                dlg.setPositiveButton("확인",null);
            }
        });
    }

}
