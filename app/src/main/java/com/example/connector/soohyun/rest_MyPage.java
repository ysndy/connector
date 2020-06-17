package com.example.connector.soohyun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class rest_MyPage extends AppCompatActivity {

    ImageView mypageImg;
    TextView mypageName;
    Button mypageEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_mypage);

        Intent intent = getIntent();

        mypageEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rest_EditPage.class);
                startActivity(intent);
            }
        });
    }

}
