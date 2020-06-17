package com.example.connector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.example.connector.activity.IntentName;
import com.example.connector.activity.MainPageActivity;
import com.example.connector.activity.ProfileListActivity;
import com.example.connector.activity.SearchActivity;
import com.example.connector.activity.SupplierProfileActivity;
import com.example.connector.sampleData.SupplierData1;
import com.example.connector.objects.Profile;
import com.example.connector.soohyun.Login;

import java.util.ArrayList;

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
    * 바인드로 정보 보내는 방향 고려 -> putExtra로 넘길 수 있음
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
        startActivity(intent);
    }

}
