package com.example.connector.doyeon.activity.mainview.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.activity.mainview.MainPageActivity;
import com.example.connector.doyeon.activity.mainview.maintab.MainTabPagerAdapter;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.lib.TranState;
import com.example.connector.doyeon.lib.TransProductAdapter;
import com.example.connector.doyeon.lib.request.CalendarRequest;
import com.example.connector.doyeon.lib.request.RestaurantInfoRequest;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.product.ProductData2;
import com.example.connector.sampleData.transaction.TransactionData2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionCalendarActivity extends Activity {

    CalendarView calendarView;
    Spinner stateSpinner;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<Product> products_all;
    ArrayList<Product> products;
    Profile myProfile;
    TextView calendarBtn;
    TextView dateTv;
    TextView priceTotalTv;
    ListView productListView;
    TransProductAdapter adapter;
    int priceTotal=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendar);
        stateSpinner = findViewById(R.id.spinner);
        calendarBtn = findViewById(R.id.calendarBtn);
        dateTv = findViewById(R.id.dateTextView);
        priceTotalTv = findViewById(R.id.priceTotalTv);
        productListView = findViewById(R.id.productListView);
        products_all = new ArrayList<>();
        products = new ArrayList<>();

        Intent intent = getIntent();
        myProfile = intent.getParcelableExtra(IntentName.PROFILE_RES);

        setProductList();

        //spinner 값 세팅
        arrayAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_dropdown_item, TranState.ARRAY);
        stateSpinner.setAdapter(arrayAdapter);

        //캘린더 날짜 변경했을 때
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            int year, month, dayOfMonth;

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                this.year = year;
                this.month = month + 1;
                this.dayOfMonth = dayOfMonth;
                String date = year + "년 " + (month + 1) + "월 " + dayOfMonth + "일";
                dateTv.setText(date);
                change();

            }

            public void change() {
                // 날짜 바뀔 때마다 서버에서 가져오면 속도가 느리니까 처음에 전체 다 가져와서 처리

                products.clear();
                String date = year + "-" + month + "-" + dayOfMonth;
                priceTotal = 0;
                for (int i = 0; i < products_all.size(); i++) {
                    Product product = products_all.get(i);
                    String date_p = product.getTransactionDate();
                    if (date_p.equals(date)) {
                        products.add(product);
                        priceTotal += product.getPrice();
                    }
                }
                //어댑터 업데이트
                adapter.setList(products);
                adapter.notifyDataSetChanged();
                //총 금액 출력
                setPriceTotal();
                Log.d("asd", "TransactionCalendarActivity.change()");

            }
        });

        //캘린더 숨기면 나타나는 날짜텍스트뷰 기본설정
        DateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date calendarViewDate = new Date(calendarView.getDate());
        final String date = format.format(calendarViewDate);
        dateTv.setText(date);
        //캘린더 숨기기
        calendarView.callOnClick();
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (calendarBtn.getText().toString().equals("달력 접기")) {
                    dateTv.setVisibility(View.VISIBLE);
                    calendarView.setVisibility(View.GONE);
                    calendarBtn.setText("달력 펼치기");
                } else {
                    dateTv.setVisibility(View.GONE);
                    calendarView.setVisibility(View.VISIBLE);
                    calendarBtn.setText("달력 접기");
                }
            }
        });

    }

    public void transactionSet() {
        //거래의 날짜와 자신의 아이디를 조건으로 하여 검색

    }

    //TODO: profile.getID()로 서버에서 데이터 불러옴
    private void setProductList() {

        //날짜 파라미터 보내서 찾고 셀렉트로 출력
        /*
        //뭘 받아올거냐?
        //상품
        이름
        수량
        공급자 ID
        */

        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jResponse = new JSONArray(response);
                    for (int i = 0; i < jResponse.length(); i++) {
                        JSONObject jso = jResponse.getJSONObject(i);
                        Product product = new Product();

                        product.setSelectedCount(Integer.parseInt(jso.getString("count")));
                        product.setName(jso.getString(IntentName.NAME));
                        product.setSupplyName(jso.getString(IntentName.SUPPLIER + "ID"));
                        product.setTransactionDate(jso.getString(IntentName.DATE));
                        product.setTransactionState(jso.getString(IntentName.STATE));
                        product.setPrice(Integer.parseInt(jso.getString(IntentName.PRICE)));
                        product.setCode(jso.getString(IntentName.PRODUCTCODE));
                        products_all.add(product);

                        priceTotal+=product.getPrice();
                        Log.d("asd", "거래 상품 정보 받아오기 성공 " + product.getTransactionDate());
                    }

                    adapter = new TransProductAdapter(products_all);
                    productListView.setAdapter(adapter);
                    setPriceTotal();

                } catch (Exception e) {
                    Log.d("asd", e.toString());
                }
            }
        };


        CalendarRequest restaurantInfoRequest = new CalendarRequest(myProfile.getId(), rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(TransactionCalendarActivity.this);
        queue.add(restaurantInfoRequest);

//        products = new ArrayList<>();
//
//        Product product = new Product();
//        product.setName(ProductData1.name);
//        product.setPrice(1);
//        product.setTransactionState(TransactionData2.state);
//        products.add(product);
//
//        Product product2 = new Product();
//        product2.setName(ProductData2.name);
//        product2.setPrice(5);
//        product2.setTransactionState(TransactionData2.state);
//        products.add(product2);
//
//        TransProductAdapter adapter = new TransProductAdapter(products);
//        productListView.setAdapter(adapter);

    }

    public void setPriceTotal() {
        priceTotalTv.setText(priceTotal+"");
    }
}
