package com.example.connector.jeongeun.providerpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.jeongeun.tabs.MyPagerAdapter;
import com.google.android.material.tabs.TabLayout;

// 공급자 마이페이지
public class Provider_mypage extends AppCompatActivity {

    ImageButton backBtn, homeBtn; // 뒤로가기, 홈
    Button editBtn; // 정보 수정
    TabLayout tabs;
    Profile profile_sup;
    TextView nameTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_mypage);
        Intent intent = getIntent();
        profile_sup = intent.getParcelableExtra(IntentName.PROFILE_SUP);

        editBtn = findViewById(R.id.editBtn);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);
        nameTV = findViewById(R.id.provider);

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
                intent.putExtra(IntentName.PROFILE_SUP, profile_sup);
                startActivity(intent);
            }
        });

        nameTV.setText(profile_sup.getName());

        //Tablayout 참조, tab 추가
        tabs = findViewById(R.id.tab);
        tabs.addTab(tabs.newTab().setText("상품"));
        tabs.addTab(tabs.newTab().setText("팔로워"));
        tabs.addTab(tabs.newTab().setText("거래처"));
        tabs.addTab(tabs.newTab().setText("현황"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), 4, profile_sup);
        viewPager.setAdapter(myPagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

    }
}

