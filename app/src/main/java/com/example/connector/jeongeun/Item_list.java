package com.example.connector.jeongeun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class Item_list extends BaseAdapter {

    private ArrayList<Product> list;

    public Item_list(ArrayList<Product> list){
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
        Item_list.CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_item_list, null, false);

            holder = new Item_list.CustomViewHolder();
            //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.proItemName = convertView.findViewById(R.id.pr_item_name);
            holder.proItemPrice = (TextView) convertView.findViewById(R.id.pr_item_price);

            convertView.setTag(holder);
        } else {
            holder = (Item_list.CustomViewHolder) convertView.getTag();
        }

        Product product = list.get(position);

        //holder.proItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.proItemName.setText(product.getName());
        holder.proItemPrice.setText(product.getPrice().toString());

        return convertView;
    }

    class CustomViewHolder { //restitem_reqdit.xml 아이디들
        ImageView proItemImg;
        TextView proItemName, proItemPrice;
    }

    public void addItem(Product pr) {
        list.add(pr);
    }
}
