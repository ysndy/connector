package com.example.connector.doyeon.activity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ScrollView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.viewpager.widget.ViewPager;

        import com.example.connector.doyeon.activity.transaction.TransactionCalendarActivity;
        import com.example.connector.doyeon.lib.ProfileButton;
        import com.example.connector.R;
        import com.example.connector.doyeon.lib.bestpager.BestPagerAdapter;
        import com.example.connector.doyeon.lib.maintab.MainTabPagerAdapter;
        import com.example.connector.jeongeun.Provider_mypage;
        import com.example.connector.sampleData.RestaurantData1;
        import com.example.connector.doyeon.objects.Profile;
        import com.google.android.material.tabs.TabLayout;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.Timer;
        import java.util.TimerTask;

public class MainPageActivity extends AppCompatActivity {

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


    Button newMoreBtn, recMoreBtn, searchBtn;
    TextView top10Btn;
    //ImageButton flipperLeftBtn, flipperRightBtn;
    ImageButton calendarBtn, homeBtn, myPageBtn;
    //ViewFlipper bestProfilesFlipper;
    ViewPager vp, bestVp;
    ArrayList<ProfileButton> bestProfileBtns;
    Profile myProfile;
    ArrayList<Profile> newProfiles, bestProfiles, recommendProfiles;
    ScrollView ms;
    TabLayout tab;
    MainTabPagerAdapter mainTabPagerAdapter;

    int currentPage = 0;

    Timer timer;
    final long DELAY_MS = 3000;           // 오토 플립용 타이머 시작 후 해당 시간에 작동(초기 웨이팅 타임) ex) 앱 로딩 후 3초 뒤 플립됨.
    final long PERIOD_MS = 5000;          // 5초 주기로 작동

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        inflating();
        setMyProfile();
        setBestProfiles(); // BEST 리스트

        top10Btn.setOnClickListener(new View.OnClickListener() {
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
        //recMoreBtn = findViewById(R.id.recommendMoreBtn);
        //newMoreBtn = findViewById(R.id.newMoreBtn);
        searchBtn = findViewById(R.id.searchBtn);
        calendarBtn = findViewById(R.id.calendarBtn);
        homeBtn = findViewById(R.id.homeBtn);
        myPageBtn = findViewById(R.id.myPageBtn);
        bestVp = findViewById(R.id.bestPager);

        vp = findViewById(R.id.pager);
        mainTabPagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager(), myProfile);
        vp.setAdapter(mainTabPagerAdapter);


    }

    public void setMyProfile(){

        //ID로 서버 데이터 조회해서 정보 받아옴
        myProfile.setCallNumber(RestaurantData1.callNumber);
        myProfile.setEmail(RestaurantData1.email);
        myProfile.setIntroduce(RestaurantData1.introduce);
        myProfile.setLocation(RestaurantData1.location);
        myProfile.setMajor(RestaurantData1.major);
        myProfile.setName(RestaurantData1.name);

    }

    public void setBestProfiles() {

        BestPagerAdapter adapter = new BestPagerAdapter(getSupportFragmentManager(), myProfile);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(bestVp);
        bestVp.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Adapter 세팅 후 타이머 실행
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                currentPage = bestVp.getCurrentItem();
                int nextPage = currentPage + 1;

                if (nextPage >= 10) {
                    nextPage = 0;
                }
                bestVp.setCurrentItem(nextPage, true);
                currentPage = nextPage;
            }
        };

        timer = new Timer(); // thread에 작업용 thread 추가
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

    }

    @Override
    public void onPause() {
        super.onPause();
        // 다른 액티비티나 프레그먼트 실행시 타이머 제거
        // 현재 페이지의 번호는 변수에 저장되어 있으니 취소해도 상관없음
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }



}
