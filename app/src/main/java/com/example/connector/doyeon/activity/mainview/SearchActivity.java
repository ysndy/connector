package com.example.connector.doyeon.activity.mainview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.connector.R;
import com.example.connector.doyeon.Filter_dialog;


public class SearchActivity extends FragmentActivity {

    Button detailSettingBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        detailSettingBtn = findViewById(R.id.detailSettingBtn);

        detailSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filter_dialog fd = Filter_dialog.getInstance();
                fd.show(getSupportFragmentManager(), Filter_dialog.TAG_EVENT_DIALOG);
            }
        });

    }
}
