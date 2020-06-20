package com.example.connector.soohyun.tabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

//팔로잉탭 어댑터
public class FollowingTab_Adapter extends BaseAdapter {

    private ArrayList<Profile> list;

    public FollowingTab_Adapter(ArrayList<Profile> list){
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

        FollowingTab_Adapter.CustomViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fragfollowing, null, false);

            holder = new FollowingTab_Adapter.CustomViewHolder();
            holder.followingItemImg = convertView.findViewById(R.id.followingItemImg);
            holder.followingItemSupply = convertView.findViewById(R.id.followingItemSupply);
            holder.followingItemIngre = convertView.findViewById(R.id.followingItemIngre);
            holder.followingItemAddre = convertView.findViewById(R.id.followingItemAddre);

            convertView.setTag(holder);
        }
        else{
            holder = (FollowingTab_Adapter.CustomViewHolder) convertView.getTag();
        }
        Profile profile = list.get(position);

        //holder.followingItemImg.
        holder.followingItemSupply.setText(profile.getName());
        holder.followingItemIngre.setText(profile.getMajor());
        holder.followingItemAddre.setText(profile.getLocation());

        return convertView;
    }

    public class CustomViewHolder {
        ImageView followingItemImg;
        TextView followingItemSupply, followingItemIngre, followingItemAddre;
    }

    public void addItem(Profile profile){
        list.add(profile);
    }
}
