package com.example.connector.jeongeun.providerpage.transaction;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.dictionary.IntentName;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

// 공급자 마이페이지 현황
public class Now_list extends BaseAdapter {

    ArrayList<Transaction> list;
    private ViewGroup parent;

    public Now_list(ArrayList<Transaction> list) {
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
        Now_list.CustomViewHolder holder;
        this.parent = parent;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_now_request_list, null, false);

            holder = new Now_list.CustomViewHolder();
            //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.resName = convertView.findViewById(R.id.pr_now_requestName);
            holder.requestDate = (TextView) convertView.findViewById(R.id.pr_now_requestDate);

            convertView.setTag(holder);
        } else {
            holder = (Now_list.CustomViewHolder) convertView.getTag();
        }

        Transaction transaction = list.get(position);

        //holder.proItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.resName.setText(transaction.getProviderName());
        holder.requestDate.setText(transaction.getDate());

        Button pr_now_requestBtn = convertView.findViewById(R.id.pr_now_requestBtn);
        pr_now_requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), Request.class);
                intent.putExtra(IntentName.TRANSACTION, list.get(position));
                parent.getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    class CustomViewHolder {
        ImageView pr_now_requestImg;
        TextView resName, requestDate;
    }

    public void addItem(Transaction t) {
        list.add(t);
    }
}