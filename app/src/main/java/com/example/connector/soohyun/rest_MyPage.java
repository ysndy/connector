package com.example.connector.soohyun;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.example.connector.jeongeun.MyPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class rest_MyPage extends AppCompatActivity {

    ImageView mypageImg;
    TextView mypageName;
    Button mypageEditBtn;
    TabLayout r_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_mypage);

        r_tab = findViewById(R.id.rest_tab);

        Intent intent = getIntent(); //인텐트

        mypageEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //정보 수정 페이지로 이동
                Intent intent = new Intent(getApplicationContext(), rest_EditPage.class);
                startActivity(intent);
            }
        });

        r_tab.addTab(r_tab.newTab().setText("좋아요"));
        r_tab.addTab(r_tab.newTab().setText("팔로잉"));
        r_tab.addTab(r_tab.newTab().setText("거래처"));
        r_tab.addTab(r_tab.newTab().setText("현황"));
        r_tab.setTabGravity(r_tab.GRAVITY_FILL);

        final ViewPager rest_viewPager = findViewById(R.id.rest_viewPager);
        final  rest_MypageAdapter rest_mypageAdapter = new rest_MypageAdapter(getSupportFragmentManager(), 4);
        rest_viewPager.setAdapter(rest_mypageAdapter);

        r_tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(rest_viewPager));
        rest_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(r_tab));


    }
}
