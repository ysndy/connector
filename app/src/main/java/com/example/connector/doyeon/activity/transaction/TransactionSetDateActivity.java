package com.example.connector.doyeon.activity.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.activity.IntentName;
import com.example.connector.doyeon.objects.Product;
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
        products = intent.getParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS);

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
                intent.putParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS, products);
                startActivity(intent);
            }
        });

    }
}
