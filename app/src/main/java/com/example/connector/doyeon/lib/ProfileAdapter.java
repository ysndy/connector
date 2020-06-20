package com.example.connector.doyeon.lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class ProfileAdapter extends BaseAdapter {
//프로필 어댑터
    private ArrayList<Profile> list;

    public ProfileAdapter(ArrayList<Profile> list){
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
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, null, false);

            holder = new CustomViewHolder();
            holder.profileImg = (ImageView) convertView.findViewById(R.id.image);
            holder.profileName = (TextView) convertView.findViewById(R.id.name);
            holder.simpleInfo = (TextView) convertView.findViewById(R.id.simpleInfo);
            holder.rating = (RatingBar) convertView.findViewById(R.id.rating);
            //holder.profileProducts = (TextView) convertView.findViewById(R.id.supplierProducts);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        Profile sp = list.get(position);

        //holder.profileImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.profileName.setText(sp.getName());
        holder.rating.setRating(sp.getRating());
        holder.simpleInfo.setText(sp.getSimpleInfo());
        //holder.profileProducts.setText(sp.getMajor());

        return convertView;
    }

    class CustomViewHolder {
        ImageView profileImg;
        TextView profileName;
        TextView simpleInfo;
        RatingBar rating;
        //TextView profileProducts;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Profile sp) {
        list.add(sp);
    }
}
