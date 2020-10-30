package com.example.connector.jeongeun.tabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class Follower_list extends BaseAdapter {

    private ArrayList<Profile> list;

    public Follower_list(ArrayList<Profile> list){
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
        Follower_list.CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_follower_list, null, false);

            holder = new Follower_list.CustomViewHolder();
            //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.restName = convertView.findViewById(R.id.followerRestName);
            holder.restCategory = convertView.findViewById(R.id.followerRestCategory);
            holder.restAddress = convertView.findViewById(R.id.followerRestAddress);

            convertView.setTag(holder);
        } else {
            holder = (Follower_list.CustomViewHolder) convertView.getTag();
        }

        Profile profile = list.get(position);

        //holder.proItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.restName.setText(profile.getName());
        holder.restCategory.setText(profile.getMajor());
        holder.restAddress.setText(profile.getLocation());

        return convertView;
    }

    class CustomViewHolder {
        ImageView proItemImg;
        TextView restName, restCategory, restAddress;
    }

    public void addItem(Profile pf) {
        list.add(pf);
    }

}
