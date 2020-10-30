package com.example.connector.doyeon.activity.mainview;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.viewpager.widget.ViewPager;

        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.toolbox.Volley;
        import com.example.connector.doyeon.activity.mainview.calendar.TransactionCalendarActivity;
        import com.example.connector.doyeon.lib.IntentName;
        import com.example.connector.R;
        import com.example.connector.doyeon.lib.request.RestaurantInfoRequest;
        import com.example.connector.doyeon.activity.mainview.bestpager.BestPagerAdapter;
        import com.example.connector.doyeon.activity.mainview.maintab.MainTabPagerAdapter;
        import com.example.connector.doyeon.lib.request.SupplierListRequest;
        import com.example.connector.jeongeun.providerpage.Provider_mypage;
        import com.example.connector.doyeon.objects.Profile;
        import com.example.connector.soohyun.restaurantpage.MyPage;
        import com.google.android.material.tabs.TabLayout;

        import org.json.JSONArray;
        import org.json.JSONObject;

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
    ImageButton calendarBtn, homeBtn, myPageBtn;
    ViewPager vp, bestVp;
    Profile myProfile;
    MainTabPagerAdapter mainTabPagerAdapter;
    int loginCode;
    final String TAG = "3322";

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

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionCalendarActivity.class);
                intent.putExtra(IntentName.PROFILE_RES, myProfile);
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

                Intent intent = new Intent(getApplicationContext(), MyPage.class);
                startActivity(intent);

                return false;
            }
        });

    }

    private void inflating(){
        myProfile = new Profile();
        //recMoreBtn = findViewById(R.id.recommendMoreBtn);
        //newMoreBtn = findViewById(R.id.newMoreBtn);
        searchBtn = findViewById(R.id.searchBtn);
        calendarBtn = findViewById(R.id.calendarBtn);
        homeBtn = findViewById(R.id.homeBtn);
        myPageBtn = findViewById(R.id.myPageBtn);
        bestVp = findViewById(R.id.bestPager);
        vp = findViewById(R.id.pager);

    }

    public void setMyProfile() {
        //외식업자일 경우
        myProfile.setId(getIntent().getStringExtra(IntentName.ID));
        //ID로 서버 데이터 조회해서 정보 받아옴

        final String userID = myProfile.getId();

        //외식업자는 메인화면부터 서버 데이터를 받아야하고 공급업자는 마이페이지에서 서버 데이터 받으면 됨 ㅇㅇ
        if (getIntent().getIntExtra(IntentName.PROFILE, 0) == IntentName.CODE_RES) {
            Response.Listener rListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jResponse = new JSONObject(response);
                        //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                        //Log.d("asd", "jResponse"+jResponse.);
                        myProfile.setCallNumber(jResponse.getString(IntentName.CALLNUMBER));
                        Log.d("asd", jResponse.getString(IntentName.CALLNUMBER));
                        myProfile.setEmail(jResponse.getString(IntentName.EMAIL));
                        Log.d("asd", jResponse.getString(IntentName.EMAIL));
                        myProfile.setIntroduce(jResponse.getString(IntentName.INFOMATION));
                        Log.d("asd", jResponse.getString(IntentName.INFOMATION));
                        myProfile.setLocation(jResponse.getString(IntentName.LOCATION));
                        Log.d("asd", jResponse.getString(IntentName.LOCATION));
                        myProfile.setMajor(jResponse.getString(IntentName.RECOMMENDS));
                        myProfile.setName(jResponse.getString(IntentName.NAME));
                        Log.d("asd", jResponse.getString(IntentName.NAME));
                        mainTabPagerAdapter = new MainTabPagerAdapter(getSupportFragmentManager(), myProfile);
                        vp.setAdapter(mainTabPagerAdapter);



                    } catch (Exception e) {
                        Log.d("asd", e.toString());
                    }
                }
            };

            RestaurantInfoRequest restaurantInfoRequest = new RestaurantInfoRequest(userID, rListener); //Request 처리 클래스
            RequestQueue queue = Volley.newRequestQueue(MainPageActivity.this);
            queue.add(restaurantInfoRequest);
            //데이터 전송에 사용할 Volley 큐 생성 및 Request 객체 추가

            //myProfile.setCallNumber(RestaurantData1.callNumber);
            //myProfile.setEmail(RestaurantData1.email);
            //myProfile.setIntroduce(RestaurantData1.introduce);
            //myProfile.setLocation(RestaurantData1.location);
            //myProfile.setMajor(RestaurantData1.major);
            //myProfile.setName(RestaurantData1.name);

        }
    }
    public void setBestProfiles() {

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Profile> profiles = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);

                    int max = 10;
                    if(jResponse.length()<10) max = jResponse.length();
                    for(int i = 0; i < max; i++){
                        JSONObject jso = jResponse.getJSONObject(i);
                        Profile profile = new Profile();

                        profile.setId(jso.getString(IntentName.ID));
                        profile.setName(jso.getString(IntentName.NAME));
                        //profile.setMajor(jso.getString(IntentName.NAME));
                        profile.setIntroduce(jso.getString(IntentName.INFOMATION));
                        profile.setLocation(jso.getString(IntentName.LOCATION));

                        profiles.add(profile);

                    }

                    BestPagerAdapter adapter = new BestPagerAdapter(getSupportFragmentManager(), myProfile, profiles);
                    TabLayout tabLayout = findViewById(R.id.tab);
                    tabLayout.setupWithViewPager(bestVp);
                    bestVp.setAdapter(adapter);
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                }catch(Exception e){
                    Log.d("asd", e.toString());
                }
            }
        };

        SupplierListRequest supplierListRequest = new SupplierListRequest(rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(MainPageActivity.this);
        queue.add(supplierListRequest);

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
