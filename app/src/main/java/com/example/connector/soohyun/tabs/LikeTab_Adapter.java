package com.example.connector.soohyun.tabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;

import java.util.ArrayList;

//좋아요탭 어댑터
public class LikeTab_Adapter extends BaseAdapter {

    private ArrayList<Product> list;

    public LikeTab_Adapter(ArrayList<Product> list) {
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
        LikeTab_Adapter.CustomViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fraglike, null, false);

            holder = new LikeTab_Adapter.CustomViewHolder();
            //holder.likeItemImg = convertView.findViewById(R.id.likeItemImg);
            holder.likeItemName = convertView.findViewById(R.id.likeItemName);
            holder.likeItemPrice = convertView.findViewById(R.id.likeItemPrice);
            holder.likeItemSupply = convertView.findViewById(R.id.likeItemSupply);

            convertView.setTag(holder);
        } else {
            holder = (LikeTab_Adapter.CustomViewHolder) convertView.getTag();
        }
        Product product = list.get(position);

        // holder.likeItemImg.setText(product.getName());
        holder.likeItemName.setText(product.getName());
        holder.likeItemPrice.setText(product.getPrice().toString());
        holder.likeItemSupply.setText(product.getSupplyName());

        return convertView;

    }


    public class CustomViewHolder {
        ImageView likeItemImg;
        TextView likeItemName, likeItemPrice, likeItemSupply;
    }

    public void addItem(Product product){
        list.add(product);
    }
}
