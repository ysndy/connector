package com.example.connector.soohyun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

public class Surpplier extends AppCompatActivity {

    private Context mContext;
    private TabLayout mTabLayout;

    ImageView proImg;
    TextView restName;
    Button editBtn;

    TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplier);

        proImg = findViewById(R.id.proImg);
        restName = findViewById(R.id.restName);
        editBtn = findViewById(R.id.editBtn);

        //Tablayout
        tabs = findViewById(R.id.tab);
        tabs.addTab(tabs.newTab().setText("좋아요"));
        tabs.addTab(tabs.newTab().setText("팔로잉"));
        tabs.addTab(tabs.newTab().setText("거래처"));
        tabs.addTab(tabs.newTab().setText("거래현황"));
        tabs.setTabGravity(tabs.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.viewPager);
        final Mypage_Adapter myPageAdapter = new Mypage_Adapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(myPageAdapter);

        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MyPage.class);
                startActivity(intent);
            }
        });

    }
}


