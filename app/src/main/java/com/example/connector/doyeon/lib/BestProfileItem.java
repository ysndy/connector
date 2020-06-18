package com.example.connector.doyeon.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.connector.R;

public class BestProfileItem extends View {
    public BestProfileItem(Context context) {
        super(context);
    }

    public BestProfileItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BestProfileItem, 0, 0);
        try {
            //mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
           // textPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
        } finally {
            a.recycle();
        }

    }

    public BestProfileItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BestProfileItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
