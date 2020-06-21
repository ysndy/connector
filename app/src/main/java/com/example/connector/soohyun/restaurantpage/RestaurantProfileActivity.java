package com.example.connector.soohyun.restaurantpage;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;
import com.example.connector.doyeon.activity.SupplierProfileActivity;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.sampleData.supplierprofile.SupplierData2;

public class RestaurantProfileActivity extends AppCompatActivity {


    ImageView proImg;
    Button callBtn, emailBtn;
    TextView intro, intro_depth;
    Profile profileSup;
    ImageButton backBtn, homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_profile);

        proImg = findViewById(R.id.proImg);
        callBtn = findViewById(R.id.callBtn);
        emailBtn = findViewById(R.id.emailBtn);
        intro = findViewById(R.id.intro);
        intro_depth = findViewById(R.id.intro_depth);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);

        Intent intent = getIntent();
        profileSup = new Profile();
        profileSup.setCallNumber(SupplierData2.callNumber);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RestaurantProfileActivity.this);
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

                android.app.AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RestaurantProfileActivity.this);
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

    }

}
