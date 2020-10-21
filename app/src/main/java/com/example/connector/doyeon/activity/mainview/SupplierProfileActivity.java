package com.example.connector.doyeon.activity.mainview;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.activity.transaction.act1.TransactionProductsActivity;
import com.example.connector.doyeon.lib.profiletab.ProfileTabPagerAdapter;
import com.example.connector.doyeon.lib.request.SupplierProductRequest;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
        //setProfileSup();
        //상품 가져오기
        setProducts();
        //setTabAdapter();
        //setInfo();
        //setProduct();
        //setProductList();
        supplierNameTV.setText(profileSup.getName());

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
                intent.putExtra(IntentName.PRODUCTS, profileSup.getProducts());
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
                        Toast.makeText(getApplicationContext(), "이메일이 복사되었습니다.", Toast.LENGTH_SHORT).show();
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
//
//        profileSup.setCallNumber(SupplierData1.callNumber);
//        profileSup.setEmail(SupplierData1.email);
//        profileSup.setIntroduce(SupplierData1.introduce);
//        profileSup.setLocation(SupplierData1.location);
//        profileSup.setMajor(SupplierData1.major);

    }

    private void setTabAdapter() {

        vp = findViewById(R.id.pager);
        ProfileTabPagerAdapter adapter = new ProfileTabPagerAdapter(getSupportFragmentManager(), profileSup);
        vp.setAdapter(adapter);

    }

    private void setProducts() {
        // 서버에서 profileSup.getId 로 조회해서 상품들 가져옴
        //profileSup.insertProducts(getApplicationContext());
        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<Product> products = new ArrayList<>();
                    JSONArray jResponse = new JSONArray(response);
                    Log.d("asd", "insertProducts - jResponse.length() = " + jResponse.length());
                    for (int i = 0; i < jResponse.length(); i++) {
                        JSONObject jso = jResponse.getJSONObject(i);

                        final Product product = new Product();
                        product.setCode(jso.getString(IntentName.CODE));
                        product.setCategory(jso.getString(IntentName.CATEGORY));
                        product.setFrom(jso.getString(IntentName.FROMTO));
                        product.setName(jso.getString(IntentName.NAME));
                        product.setPrice(jso.getInt(IntentName.PRICE));
                        product.setImageUrl(jso.getString(IntentName.IMG));
                        products.add(product);

                    }
                    profileSup.setProducts(products);
                    setTabAdapter();
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);


                } catch (Exception e) {
                    Log.d("asd", "Profile.java - " + e.toString());
                }
            }
        };

        SupplierProductRequest supplierProductRequest = new SupplierProductRequest(profileSup.getId(), rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(supplierProductRequest);
        //Log.d("asd", "Profile_products.size = "+products.size());
    }

    private void inflating() {

        profileSup = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_SUP);
        profileRes = (Profile) getIntent().getParcelableExtra(IntentName.PROFILE_RES);
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
