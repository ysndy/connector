package com.example.connector.soohyun.shared;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.MainActivity;
import com.example.connector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

    EditText joinId, joinPass, joinPass2, joinEmail, bodyNum, tailNum;
    TextView joinAgree;
    Button goBtn, IDver, emailver, agreeBtn;
    CheckBox checkAgree;
    ImageButton backBtn;
    Spinner headNum; //휴대폰번호앞


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Intent intent = getIntent();

        goBtn = findViewById(R.id.goBtn);
        joinId = findViewById(R.id.joinId);
        joinPass = findViewById(R.id.joinPass);
        joinPass2 = findViewById(R.id.joinPass2);
        joinEmail = findViewById(R.id.joinEmail);
        headNum = findViewById(R.id.headNum);
        bodyNum = findViewById(R.id.bodyNum);
        tailNum = findViewById(R.id.tailNum);
        joinAgree = findViewById(R.id.goBtn);
        checkAgree = findViewById(R.id.checkAgree);
        IDver = findViewById(R.id.IDver);
        emailver = findViewById(R.id.email_ver);
        backBtn = findViewById(R.id.backBtn);
        agreeBtn = findViewById(R.id.agreeBtn);


        final String[] spinNum = {"010", "016", "017", "018", "019", "011"}; //전화번호 앞자리 배열데이터

        ArrayAdapter<String> adapter; //전화번호 앞
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinNum);
        headNum.setAdapter(adapter);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg_Commit();
            }
        });

        IDver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
                dlg.setTitle("아이디 중복확인");
                dlg.setMessage("사용가능한 ID입니다.");
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupAgree.class);
                startActivity(intent);
            }
        });

//        emailver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
//                dlg.setTitle("이메일 인증");
//                dlg.setMessage("입력하신 이메일의 메일함을 확인하여 인증해주세요.");
//                dlg.setPositiveButton("확인",null);
//                dlg.show();
//            }
//        });
    }

    private void msg_Commit(){
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
}

