package com.example.connector.jeongeun;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class Now_deal_list extends BaseAdapter {

    ArrayList<Transaction> list;
    private ViewGroup parent;

    public Now_deal_list(ArrayList<Transaction> list) {
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        Now_deal_list.CustomViewHolder holder;
        this.parent = parent;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_now_deal_list, null, false);

            holder = new Now_deal_list.CustomViewHolder();
            //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.dealName = convertView.findViewById(R.id.pr_now_dealName);
            holder.dealDate = (TextView) convertView.findViewById(R.id.pr_now_dealDate);

            convertView.setTag(holder);
        } else {
            holder = (Now_deal_list.CustomViewHolder) convertView.getTag();
        }

        Transaction transaction = list.get(position);

        //holder.proItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.dealName.setText(transaction.getSupplyName());
        holder.dealDate.setText(transaction.getDate());

        // 거래진행중
        Button pr_now_dealBtn = convertView.findViewById(R.id.pr_now_dealBtn);
        pr_now_dealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), Deal.class);
                parent.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    class CustomViewHolder {
        ImageView pr_now_dealImg;
        TextView dealName, dealDate;
    }

    public void addItem(Transaction t) {
        list.add(t);
    }
}

