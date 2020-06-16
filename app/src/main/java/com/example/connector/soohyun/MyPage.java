package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class MyPage extends AppCompatActivity {

    ImageView proImEdi;
    Button imgBtn, editSuc;
    TextView name, email, number, introText;
    EditText nameEdit, emailEdit, numEdit, introEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        Intent intent = getIntent();

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //사진 앱에서 올리기
            }
        });


    editSuc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "수정완료", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder dlg = new AlertDialog.Builder(MyPage.this);
            dlg.setTitle("수정완료");
            dlg.setMessage("수정되었습니다");
            dlg.setPositiveButton("확인", null);
            dlg.show();
        }
    });

    }
}
