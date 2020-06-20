package com.example.connector.doyeon.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.connector.R;
import com.example.connector.doyeon.activity.transaction.TransactionProductsActivity;
import com.example.connector.doyeon.lib.profiletab.ProfileTabPagerAdapter;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.SupplierData1;

public class SupplierProfileActivity extends AppCompatActivity {

    private Profile profileSup;
    private Profile profileRes;
    //private ArrayList<Product> products;
    private Button callBtn, transactionBtn, followBtn, emailBtn; //, productListBtn, informationBtn;
    private ImageButton backBtn, homeBtn;
    private TextView supplierNameTV; //, locationTV, introduceTV, majorTV;
    private ViewPager vp;

    //private ListView productListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 서버에서 ID로 조회해서 상품목록 가져오기
        // profile 데이터를 못받아왔을 때 예외처리

        inflating();
        setProfileSup();
        setTabAdapter();
       // setInfo();
        //setProduct();
        //setProductList();

        //전화버튼
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SupplierProfileActivity.this);
                builder.setTitle("전화");
                builder.setMessage(profileSup.getCallNumber());
                builder.setPositiveButton("전화걸기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tel = "tel:" + profileSup.getCallNumber();
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                    }
                });
                builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        //거래신청
        transactionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionProductsActivity.class);
                intent.putExtra(IntentName.PROFILE_SUP, (Parcelable) profileSup);
                intent.putExtra(IntentName.PROFILE_RES, (Parcelable) profileRes);
                startActivity(intent);
            }
        });

        //팔로우
        followBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profileRes.getId(), profileSup.getId() follow 테이블에 추가
                Toast.makeText(getApplicationContext(), profileSup.getName() + "을 팔로우하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //이메일
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SupplierProfileActivity.this);
                builder.setTitle("이메일");
                builder.setMessage(profileSup.getEmail());
                builder.setPositiveButton("이메일 복사", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("simple text", profileSup.getEmail());
                        clipboard.setPrimaryClip(clip);
                    }
                });
                builder.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setProfileSup() {

        profileSup.setCallNumber(SupplierData1.callNumber);
        profileSup.setEmail(SupplierData1.email);
        profileSup.setIntroduce(SupplierData1.introduce);
        profileSup.setLocation(SupplierData1.location);
        profileSup.setMajor(SupplierData1.major);

    }

    private void setTabAdapter(){

        vp = findViewById(R.id.pager);
        ProfileTabPagerAdapter adapter = new ProfileTabPagerAdapter(getSupportFragmentManager(), profileSup);
        vp.setAdapter(adapter);

    }

    private void setProduct() {
        // 서버에서 profileSup.getId 로 조회해서 상품들 가져옴
        profileSup.insertProducts();
    }

    private void inflating() {

        profileSup = (Profile) getIntent().getSerializableExtra(IntentName.PROFILE_SUP);
        profileRes = (Profile) getIntent().getSerializableExtra(IntentName.PROFILE_RES);
        callBtn = findViewById(R.id.callBtn);
        transactionBtn = findViewById(R.id.transactionBtn);
        followBtn = findViewById(R.id.followBtn);
        emailBtn = findViewById(R.id.emailBtn);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);
        supplierNameTV = findViewById(R.id.supplierNameTV);

    }

    private void setImage() {

    }

}
