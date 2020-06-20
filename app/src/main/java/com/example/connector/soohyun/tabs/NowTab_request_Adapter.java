package com.example.connector.soohyun.tabs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class NowTab_request_Adapter extends BaseAdapter {

    private ArrayList<Transaction> list;
    public NowTab_request_Adapter(ArrayList<Transaction> list){
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
        NowTab_request_Adapter.CustomViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fragnow1, null, false);

            holder = new NowTab_request_Adapter.CustomViewHolder();
            holder.now1ItemImg = convertView.findViewById(R.id.now1ItemImg);
            holder.now1ItemSupply = convertView.findViewById(R.id.now1ItemSupply);
            holder.now1ItemReqDate = convertView.findViewById(R.id.now1ItemReqDate);

            convertView.setTag(holder);
        }
        else{
            holder = (NowTab_request_Adapter.CustomViewHolder) convertView.getTag();
        }
        Transaction transaction = list.get(position);

        holder.now1ItemSupply.setText(transaction.getSupplyName());
        holder.now1ItemReqDate.setText(transaction.getDate());

        return convertView;
    }

    public class CustomViewHolder {
        ImageView now1ItemImg;
        TextView now1ItemSupply, now1ItemReqDate;
    }
    public void addItem(Transaction transaction){
        list.add(transaction);
    }
}
