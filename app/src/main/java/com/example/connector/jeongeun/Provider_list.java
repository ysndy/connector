package com.example.connector.jeongeun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class Provider_list extends BaseAdapter {

    private ArrayList<Profile> list;

    public Provider_list(ArrayList<Profile> list){
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Provider_list.CustomViewHolder holder;
        if (convertView == null) {
            // 레이아웃 바꾸기
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_restaurant_list, null, false);

            holder = new Provider_list.CustomViewHolder();
            //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.pro_restName = convertView.findViewById(R.id.providerRestName);
            holder.restPhone = convertView.findViewById(R.id.providerRestPnum);
            holder.restTotal = convertView.findViewById(R.id.providerRestTotal);

            convertView.setTag(holder);
        } else {
            holder = (Provider_list.CustomViewHolder) convertView.getTag();
        }

        Profile profile = list.get(position);

        //holder.proItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.pro_restName.setText(profile.getName());
        holder.restPhone.setText(profile.getCallNumber());
        holder.restTotal.setText(profile.getLocation()); // 총매출 바꿔야함

        return convertView;
    }

    class CustomViewHolder {
        ImageView proItemImg;
        TextView pro_restName, restPhone, restTotal;
    }

    public void addItem(Profile pf) {
        list.add(pf);
    }
}
