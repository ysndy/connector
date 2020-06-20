package com.example.connector.soohyun.tabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class NowTab_request_Adapter extends BaseAdapter {

    private ArrayList<Transaction> list;

    public NowTab_request_Adapter(ArrayList<Transaction> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NowTab_ing_Adapter.CustomViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fragnow1,null,false);


        }

        return convertView;
    }

    public class CustomViewHolder  {
    }
}
