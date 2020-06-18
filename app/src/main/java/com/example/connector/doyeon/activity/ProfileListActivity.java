package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class ProfileListActivity extends Activity {

    private ArrayList<Profile> profiles;
    private String resMajor;
    private int listCode;

    private ListView profileListView;
    private TextView topTextView;

    /*
    * 실행시킨 버튼이 Top, New, Recommend 중 무엇인지 확인
    * TextView 변경
    * 서버데이터베이스
    * listCode 확인
    * LIST_TOP - 별점순으로 소팅하고 profiles에 데이터 10개 저장
    * LIST_NEW - profiles에 데이터 10개 저장
    * LIST_RECOMMEND - resMajor 조건검색으로 데이터 10개 저장
    *
    * */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilelist);
        inflating();


    }

    private void inflating(){

        profileListView = findViewById(R.id.profileListView);
        topTextView = findViewById(R.id.topTextView);

    }

    private void topListSet(){

    }

    private void newListSet(){

    }

    private void recommendListSet(){

    }

}
