package com.example.connector.jeongeun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

// 공급자 마이페이지
public class Provider_mypage extends AppCompatActivity {

    ImageButton backBtn, homeBtn; // 뒤로가기, 홈
    Button editBtn; // 정보 수정
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_mypage);

        //ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        editBtn = findViewById(R.id.editBtn);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

        // 뒤로가기
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 홈
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 정보 수정
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Provider_mypage.this, Edit_info.class);
                startActivity(intent);
            }
        });

        //Tablayout 참조, tab 추가
        tabs = findViewById(R.id.tab);
        tabs.addTab(tabs.newTab().setText("상품"));
        tabs.addTab(tabs.newTab().setText("팔로워"));
        tabs.addTab(tabs.newTab().setText("거래처"));
        tabs.addTab(tabs.newTab().setText("현황"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(myPagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

    }
}

