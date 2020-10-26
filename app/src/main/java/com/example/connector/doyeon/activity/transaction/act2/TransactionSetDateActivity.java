package com.example.connector.doyeon.activity.transaction.act2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.activity.transaction.act3.TosActivity;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.objects.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionSetDateActivity extends Activity {
    CalendarView calendarView;
    String date;
    Button completeBtn;
    Transaction transaction;
    ArrayList<Product> products;
    Profile profile_sup, profile_res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_calendar);

        //인플레이팅
        calendarView = findViewById(R.id.calendar);
        completeBtn = findViewById(R.id.completeBtn);

        //인텐트
        Intent intent = getIntent();
        transaction = intent.getParcelableExtra(IntentName.TRANSACTION);
        //profile_sup = intent.getParcelableExtra(IntentName.PROFILE_SUP);
        //profile_res = intent.getParcelableExtra(IntentName.PROFILE_RES);
        products = intent.getParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS);
        Log.d("asd", products.get(0).getSelectedCount()+"개 1번째 상품 act2");

        //시작할 때 선택된 일자 적용(오늘)
        Date fDate = new Date(calendarView.getDate());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(fDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year+"-"+(month+1)+"-"+dayOfMonth;
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.setDate(date);
                Intent intent = new Intent(getApplicationContext(), TosActivity.class);
                intent.putExtra(IntentName.TRANSACTION, transaction);
                //intent.putExtra(IntentName.PROFILE_SUP, profile_sup);
                //intent.putExtra(IntentName.PROFILE_RES, profile_res);
                intent.putParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS, products);
                startActivity(intent);
            }
        });

    }
}
