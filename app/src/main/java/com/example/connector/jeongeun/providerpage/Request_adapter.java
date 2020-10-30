package com.example.connector.jeongeun.providerpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class Request_adapter extends BaseAdapter {

    private ArrayList<Transaction> list;
    private ViewGroup parent;
    EditText reqItemDate;

    public Request_adapter(ArrayList<Transaction> list) {
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
    public View getView(int position, View convertView, final ViewGroup parent) {
        Request_adapter.CustomViewHolder holder;
        this.parent = parent;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_now_request_list_custom, null, false);

            holder = new Request_adapter.CustomViewHolder();
            //holder.reqItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.reqItemName = convertView.findViewById(R.id.reqItemName);
            holder.reqItemAE = (TextView) convertView.findViewById(R.id.reqItemAE);
            holder.reqItemPrice = (TextView) convertView.findViewById(R.id.reqItemPrice);
            holder.reqItemDate = (TextView) convertView.findViewById(R.id.reqItemDate);


            convertView.setTag(holder);
        } else {
            holder = (Request_adapter.CustomViewHolder) convertView.getTag();
        }

        Transaction transaction = list.get(position);

        //holder.reqItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.reqItemName.setText(transaction.getProducts().get(0).getName());
        holder.reqItemAE.setText(transaction.getCount().toString());
        holder.reqItemPrice.setText(transaction.getPriceTotal().toString());
        holder.reqItemDate.setText(transaction.getDate());

        // 날짜 수정 기능
      /*  reqItemDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }); */

        return convertView;
    }

    class CustomViewHolder {
        ImageView reqItemImg;
        TextView reqItemName, reqItemAE, reqItemPrice, reqItemDate;
    }

    public void addItem(Transaction t) {
        list.add(t);
    }
}
