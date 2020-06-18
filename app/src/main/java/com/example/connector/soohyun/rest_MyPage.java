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

import com.example.connector.R;
import com.google.android.material.tabs.TabLayout;

public class rest_MyPage extends ActivityGroup {

    ImageView mypageImg;
    TextView mypageName;
    Button mypageEditBtn;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_mypage);

        Intent intent = getIntent(); //인텐트

        tabHost = findViewById(R.id.tab);
        tabHost.setup(getLocalActivityManager());

        //TabHost tabHost = getTabhost();

        mypageEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), rest_EditPage.class);
                startActivity(intent);
            }
        });
/*
        //탭만들기
        TabHost.TabSpec tabItem = tabHost.newTabSpec("좋아요");
        tabItem.setContent(R.id.tab_item);
        tabHost.addTab(tabItem);

        TabHost.TabSpec tabFollowing = tabHost.newTabSpec("팔로잉");
        tabItem.setContent(R.id.tab_following);
        tabHost.addTab(tabItem);

        TabHost.TabSpec tabGorae = tabHost.newTabSpec("거래처");
        tabItem.setContent(R.id.tab_gorae);
        tabHost.addTab(tabItem);

        TabHost.TabSpec tabNowing = tabHost.newTabSpec("거래현황");
        tabItem.setContent(R.id.tab_nowing);
        tabHost.addTab(tabItem);

        tabHost.setCurrentTab(0);
    }*/
    }
}
