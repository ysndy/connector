package com.example.connector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.connector.activity.ProfileListActivity;
import com.example.connector.objects.Profile;

public class MainActivity extends AppCompatActivity {

    /* 입력된 ID를 가지고 서버에서 정보를 가져옴
    *   공급처인지 외식업자인지 비로그인인지 구분
    *   일단 공급처 프로필은 띄움. 그럴려면 공급처 가져오는 메소드 필요
    *   BEST에 별점순서대로 4개 노출. TOP10 버튼 누르면 10개 노출 / 모든 아이디 공통
    *   NEW에 최근생성순서대로 3개 노출. 더보기 버튼 누르면 10개 노출 / 모든 아이디 공통
    *   Recommend에 major가 맞는 공급처 3개 노출. 더보기 버튼 누르면 10개 노출. / major 비교. 비로그인일 경우 비활성화

    *
    * 캘린더 버튼
    * 캘린더 화면/id 인텐트
    *
    * 홈버튼
    * 실행된 액티비티 finish();
    *
    * 마이페이지 버튼
    * 공급처일 경우 공급처 마이페이지/id 인텐트
    * 외식업자일 경우 외식업자 마이페이지/id 인텐트
    *
    * 바인드로 정보 보내는 방향 고려
    * */


    Button top10Btn, newMoreBtn, recMoreBtn;
    ListView newListView;
    Profile myProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inflating();
        setMyProfile();

        top10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileListActivity.class);

            }
        });

    }

    private void inflating(){
        myProfile = new Profile();
        top10Btn = findViewById(R.id.top10Btn);
        newMoreBtn = findViewById(R.id.newMoreBtn);
        newListView = findViewById(R.id.newListView);
    }

    public void setMyProfile(){
    }

}
