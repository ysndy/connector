package com.example.connector.jeongeun.providerpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class Deal_adapter extends BaseAdapter {

    private ArrayList<Transaction> list;
    private ViewGroup parent;

    public Deal_adapter(ArrayList<Transaction> list) {
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
        Deal_adapter.CustomViewHolder holder;
        this.parent = parent;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_now_deal_list_custom, null, false);

            holder = new Deal_adapter.CustomViewHolder();
            //holder.reqItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.dealItemName = convertView.findViewById(R.id.dealItemName);
            holder.dealItemAE = (TextView) convertView.findViewById(R.id.dealItemAE);
            holder.dealItemPrice = (TextView) convertView.findViewById(R.id.dealItemPrice);
            holder.dealItemDate = (TextView) convertView.findViewById(R.id.dealItemDate);


            convertView.setTag(holder);
        } else {
            holder = (Deal_adapter.CustomViewHolder) convertView.getTag();
        }

        Transaction transaction = list.get(position);

        //holder.dealItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.dealItemName.setText(transaction.getProducts().get(0).getName());
        holder.dealItemAE.setText(transaction.getCount().toString());
        holder.dealItemPrice.setText(transaction.getPriceTotal().toString());
        holder.dealItemDate.setText(transaction.getDate());

        return convertView;
    }

    class CustomViewHolder {
        ImageView dealItemImg;
        TextView dealItemName, dealItemAE, dealItemPrice, dealItemDate;
    }

    public void addItem(Transaction t) {
        list.add(t);
    }
}

