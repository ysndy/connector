package com.example.connector.doyeon.activity.transaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.lib.ProductAdapter;
import com.example.connector.doyeon.lib.TransProductAdapter;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.product.ProductData1;
import com.example.connector.sampleData.product.ProductData2;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionCalendarActivity extends Activity {

    CalendarView calendarView;
    Spinner stateSpinner;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<Product> products;
    Profile myProfile;
    TextView calendarBtn;
    TextView dateTv;
    ListView productListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendar);
        stateSpinner = findViewById(R.id.spinner);
        calendarBtn = findViewById(R.id.calendarBtn);
        dateTv = findViewById(R.id.dateTextView);
        productListView = findViewById(R.id.productListView);

        setProductList();

        //spinner 값 세팅
        arrayAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_dropdown_item, TranState.ARRAY);
        stateSpinner.setAdapter(arrayAdapter);

        //캘린더 날짜 변경했을 때
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = year+"년 "+(month+1)+"월 "+dayOfMonth+"일";
                dateTv.setText(date);
            }
        });

        //캘린더 숨기면 나타나는 날짜텍스트뷰 기본설정
        DateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date calendarViewDate = new Date(calendarView.getDate());
        final String date = format.format(calendarViewDate);
        dateTv.setText(date);

        //캘린더 숨기기
        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendarBtn.getText().toString().equals("달력 접어두기")){
                    dateTv.setVisibility(View.VISIBLE);
                    calendarView.setVisibility(View.GONE);
                    calendarBtn.setText("달력 펼치기");
                }

                else {
                    dateTv.setVisibility(View.GONE);
                    calendarView.setVisibility(View.VISIBLE);
                    calendarBtn.setText("달력 접어두기");
                }
            }
        });

    }

    public void transactionSet(){
        //거래의 날짜와 자신의 아이디를 조건으로 하여 검색

    }

    //TODO: profile.getID()로 서버에서 데이터 불러옴
    private void setProductList() {

        products = new ArrayList<>();

        Product product = new Product();
        product.setName(ProductData1.name);
        product.setPrice(1);
        product.setTransactionState(TransactionData2.state);
        products.add(product);

        Product product2 = new Product();
        product2.setName(ProductData2.name);
        product2.setPrice(5);
        product2.setTransactionState(TransactionData2.state);
        products.add(product2);

        TransProductAdapter adapter = new TransProductAdapter(products);
        productListView.setAdapter(adapter);

    }



}
