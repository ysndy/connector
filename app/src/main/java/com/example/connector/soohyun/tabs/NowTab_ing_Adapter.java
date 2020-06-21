package com.example.connector.soohyun.tabs;

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
import com.example.connector.soohyun.shared.Login;

import java.util.ArrayList;

public class NowTab_ing_Adapter extends BaseAdapter {

    private ArrayList<Transaction> list;

    public NowTab_ing_Adapter(ArrayList<Transaction> list) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fragnow2,null,false);

            holder = new NowTab_ing_Adapter.CustomViewHolder();
            holder.now2ItemImg = convertView.findViewById(R.id.now2ItemImg);
            holder.now2ItemSupply = convertView.findViewById(R.id.now2ItemSupply);
            holder.now2ItemReqDate = convertView.findViewById(R.id.now2ItemReqDate);
            holder.yellBtn = convertView.findViewById(R.id.yellBtn);

            convertView.setTag(holder);
        }
        else {
            holder = (NowTab_ing_Adapter.CustomViewHolder) convertView.getTag();
        }
        Transaction transaction = list.get(position);

        //holder.now2ItemImg
        holder.now2ItemSupply.setText(transaction.getSupplyName());
        holder.now2ItemReqDate.setText(transaction.getDate());
       /*
        holder.yellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        }); */

        return convertView;
    }

    public class CustomViewHolder {

        ImageView now2ItemImg;
        TextView now2ItemSupply, now2ItemReqDate;
        Button yellBtn;
    }

    public void addItem(Transaction transaction){
        list.add(transaction);
    }
}
