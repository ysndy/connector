package com.example.connector.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.connector.ProfileAdapter;
import com.example.connector.ProfileButton;
import com.example.connector.R;
import com.example.connector.activity.IntentName;
import com.example.connector.activity.ProfileListActivity;
import com.example.connector.activity.SearchActivity;
import com.example.connector.activity.SupplierProfileActivity;
import com.example.connector.jeongeun.Provider_mypage;
import com.example.connector.sampleData.SupplierData1;
import com.example.connector.objects.Profile;

import java.io.Serializable;
import java.security.Provider;
import java.util.ArrayList;

public class MainPageActivity extends Activity {

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


    Button top10Btn, newMoreBtn, recMoreBtn, searchBtn;
    ImageButton flipperLeftBtn, flipperRightBtn;
    ImageButton calendarBtn, homeBtn, myPageBtn;
    ListView newListView, recommendListView;
    ViewFlipper bestProfilesFlipper;
    ArrayList<ProfileButton> bestProfileBtns;
    Profile myProfile;
    ArrayList<Profile> newProfiles, bestProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        inflating();
        setMyProfile();
        setNewProfiles();
        setBestProfiles();

        newListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SupplierProfileActivity.class);
                intent.putExtra(IntentName.PROFILE_SUP, (Serializable) newProfiles.get(position));
                intent.putExtra(IntentName.PROFILE_RES, (Serializable) myProfile);
                startActivity(intent);
            }
        });

        top10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileListActivity.class);
                startActivity(intent);
            }
        });

        newMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileListActivity.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra(IntentName.PROFILE_RES, (Serializable) myProfile);
                startActivity(intent);

            }
        });

        flipperLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bestProfilesFlipper.showPrevious();
            }
        });
        flipperRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bestProfilesFlipper.showNext();
            }
        });

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionCalendarActivity.class);
                startActivity(intent);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Provider_mypage.class);
                startActivity(intent);

            }
        });

        myPageBtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Provider_mypage.class);
                startActivity(intent);

                return false;
            }
        });

    }

    private void inflating(){
        myProfile = new Profile();
        top10Btn = findViewById(R.id.top10Btn);
        recMoreBtn = findViewById(R.id.recommendMoreBtn);
        newMoreBtn = findViewById(R.id.newMoreBtn);
        searchBtn = findViewById(R.id.searchBtn);
        newListView = findViewById(R.id.newListView);
        recommendListView = findViewById(R.id.recommendListView);
        bestProfilesFlipper = findViewById(R.id.bestProfileFlipper);
        flipperLeftBtn = findViewById(R.id.flipperLeftBtn);
        flipperRightBtn = findViewById(R.id.flipperRightBtn);
        calendarBtn = findViewById(R.id.calendarBtn);
        homeBtn = findViewById(R.id.homeBtn);
        myPageBtn = findViewById(R.id.myPageBtn);
    }

    public void setMyProfile(){

        //ID로 서버 데이터 조회해서 정보 받아옴
        myProfile.setCallNumber(SupplierData1.callNumber);
        myProfile.setEmail(SupplierData1.email);
        myProfile.setIntroduce(SupplierData1.introduce);
        myProfile.setLocation(SupplierData1.location);
        myProfile.setMajor(SupplierData1.major);
        myProfile.setName(SupplierData1.name);

    }

    public void setNewProfiles(){

        newProfiles = new ArrayList<>();
        for(int i=0; i<3; i++){

            Profile profile = new Profile();
            profile.setId(SupplierData1.id);
            profile.setName(SupplierData1.name);
            profile.setMajor(SupplierData1.major);
            newProfiles.add(profile);

        }

        ProfileAdapter adapter = new ProfileAdapter(newProfiles);
        newListView.setAdapter(adapter);

    }

    public void setRecommendProfiles(){

    }

    public void setBestProfiles(){

        bestProfiles = new ArrayList<>();
        bestProfileBtns = new ArrayList<>();
        for(int i=0; i<4; i++){

            Profile profile = new Profile();
            profile.setId(SupplierData1.id);
            profile.setName(SupplierData1.name);
            profile.setMajor(SupplierData1.major);
            bestProfiles.add(profile);

            ProfileButton btn = new ProfileButton(getApplicationContext());
            btn.setText(SupplierData1.name);
            btn.setProfile(profile);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SupplierProfileActivity.class);
                    intent.putExtra(IntentName.PROFILE_RES, (Serializable)myProfile);
                    intent.putExtra(IntentName.PROFILE_SUP, (Serializable) ((ProfileButton)v).getProfile());
                    startActivity(intent);
                }
            });

            bestProfilesFlipper.addView(btn, new ViewFlipper.LayoutParams(ViewFlipper.LayoutParams.MATCH_PARENT, ViewFlipper.LayoutParams.MATCH_PARENT));
        }
        //

    }

}
