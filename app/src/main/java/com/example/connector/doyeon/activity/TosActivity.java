package com.example.connector.doyeon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class TosActivity extends Activity {

    Profile profile_res, profile_sup;
    Transaction transaction;
    ArrayList<Product> transactionProducts;
    CheckBox tos1, tos2;
    Button goBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_terms_and_conditions);

        tos1 = findViewById(R.id.tos1CheckBox);
        tos2 = findViewById(R.id.tos2CheckBox);
        goBtn = findViewById(R.id.continueBtn);

        Intent intent = getIntent();
        transactionProducts = intent.getParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS);
        transaction = intent.getParcelableExtra(IntentName.TRANSACTION);
        Log.d("asd", transactionProducts.size()+"");
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tos1.isChecked() && tos2.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), TransactionRequestActivity.class);
                    intent.putParcelableArrayListExtra(IntentName.SELECTED_PRODUCTS, transactionProducts);
                    intent.putExtra(IntentName.TRANSACTION, transaction);
                    startActivity(intent);
                }else if(!tos1.isChecked()){
                    Toast.makeText(getApplicationContext(), "1번 약관에 동의해주세요", Toast.LENGTH_SHORT).show();
                }else if(!tos2.isChecked()){
                    Toast.makeText(getApplicationContext(), "2번 약관에 동의해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
