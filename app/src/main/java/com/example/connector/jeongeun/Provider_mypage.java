package com.example.connector.jeongeun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

public class Provider_mypage extends AppCompatActivity {

    Button editBtn;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_mypage);

        editBtn = findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Provider_mypage.this, Edit_info.class);
                startActivity(intent);
            }
        });

        //Tablayout
        tabs = findViewById(R.id.tab);
        tabs.addTab(tabs.newTab().setText("상품"));
        tabs.addTab(tabs.newTab().setText("팔로워"));
        tabs.addTab(tabs.newTab().setText("거래처"));
        tabs.addTab(tabs.newTab().setText("거래현황"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(myPagerAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

    }
}

