package com.example.connector.doyeon.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

public class BestProfileItem extends LinearLayout {

    private ImageView profile_img;
    private TextView profile_name_TV;
    private Profile profile;

    public BestProfileItem(Context context) {
        super(context);
        initViews(context, null);
    }

    public BestProfileItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);

    }

    public BestProfileItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);

    }

    public BestProfileItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context, attrs);

    }

    private void initViews(Context context, AttributeSet attrs){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_flipper_profile, this);
        if(attrs!=null){
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BestProfileItem);
            a.recycle();
        }

    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        profile_img = (ImageView) findViewById(R.id.bestProfileImageView);
        profile_name_TV = (TextView) findViewById(R.id.bestProfileName);

    }

}
