package com.example.connector.soohyun;

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

public class Join extends AppCompatActivity {

    EditText joinId, jpinPass, joinEmail, joinNum;
    TextView joinAgree;
    Button goBtn;
    CheckBox checkAgree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Intent intent = getIntent();

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Join.this);
                dlg.setTitle("회원가입 완료");
                dlg.setMessage("축하합니다. \n Go래처 회원이 되었습니다.");
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });
    }
}
