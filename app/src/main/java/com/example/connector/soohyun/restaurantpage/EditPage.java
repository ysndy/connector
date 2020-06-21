package com.example.connector.soohyun.restaurantpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

//마이페이지 수정하는 페이지
public class EditPage extends AppCompatActivity {

    ImageView proImgEdi;
    Button imgBtn,editSuc;
    TextView name, email, number, introText;
    EditText nameEdit, emailEdit, numEdit, introEdit;
    ImageButton backBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_editpage);

        imgBtn = findViewById(R.id.imgBtn);
        editSuc = findViewById(R.id.editSuc);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

        Intent intent = getIntent(); //인텐트

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() { //사진올리기 버튼 > 사진 앱 접근
            @Override
            public void onClick(View v) {
                //사진올리기
            }
        });


        editSuc.setOnClickListener(new View.OnClickListener() { //수정완료 버튼 > 수정완료된 화면으로 업데이트
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "수정완료", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder dlg = new AlertDialog.Builder(EditPage.this);
                dlg.setTitle("수정완료");
                dlg.setMessage("수정되었습니다.");
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });
    }

}
