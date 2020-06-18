package com.example.connector.soohyun.restaurantpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.R;

public class NowRequest extends AppCompatActivity {

    TextView nowName, nowDate;
    ImageView nowproImg;
    ListView listView_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_nowrequest);

        Intent intent = getIntent();

        nowName = findViewById(R.id.nowName);
        nowDate = findViewById(R.id.joinId);
        nowproImg = findViewById(R.id.nowproImg);


        listView_now = findViewById(R.id.now_listview);

        //restListAdapter_reqedit



    }

}
