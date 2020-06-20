package com.example.connector.soohyun.tabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class GoraeTab_Adapter extends BaseAdapter {

    private ArrayList<Profile> list;

    public GoraeTab_Adapter(ArrayList<Profile> list){
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
        GoraeTab_Adapter.CustomViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fraggorae, null, false);

            holder = new GoraeTab_Adapter.CustomViewHolder();
            holder.goraeItemImg = convertView.findViewById(R.id.goraeItemImg);
            holder.goraeItemSupply = convertView.findViewById(R.id.goraeItemSupply);
            holder.goraeItemContact = convertView.findViewById(R.id.goraeItemContact);
            holder.goraeItemTotal = convertView.findViewById(R.id.goraeItemTotal);
            //holder.giveStar = convertView.findViewById(R.id.giveStar);

            convertView.setTag(holder);
        } else {
            holder = (GoraeTab_Adapter.CustomViewHolder) convertView.getTag();
        }
        Profile profile = list.get(position);

        // holder.goraeItemImg.setText(product.getName());
        holder.goraeItemSupply.setText(profile.getName());
        holder.goraeItemContact.setText(profile.getCallNumber());
        holder.goraeItemTotal.setText(profile.getProducts().get(0).getPrice().toString());
        //holder.giverStar.setOnClickListener();

        return convertView;

    }


    public class CustomViewHolder {
        ImageView goraeItemImg;
        TextView goraeItemSupply, goraeItemContact, goraeItemTotal;
        Button giverStar;
    }

    public void addItem(Profile profile){
        list.add(profile);
    }
}
