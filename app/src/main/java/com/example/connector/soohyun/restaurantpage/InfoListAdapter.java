package com.example.connector.soohyun.restaurantpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

public class InfoListAdapter extends BaseAdapter {
    // 공급처 프로필에서 보여질 상품 리스트 어댑터
    private ArrayList<Transaction> list;

    public InfoListAdapter(ArrayList<Transaction> list){
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
        InfoListAdapter.CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_reqedit, null, false);

            holder = new InfoListAdapter.CustomViewHolder();
            //holder.reqItemImg = (ImageView) convertView.findViewById(R.id.image);
            holder.reqItemName = convertView.findViewById(R.id.reqItemName);
            holder.reqItemAE = (TextView) convertView.findViewById(R.id.reqItemAE);
            holder.reqItemPrice = (TextView) convertView.findViewById(R.id.reqItemPrice);
            holder.reqItemDate  = (TextView) convertView.findViewById(R.id.reqItemDate);


            convertView.setTag(holder);
        } else {
            holder = (InfoListAdapter.CustomViewHolder) convertView.getTag();
        }

        Transaction transaction = list.get(position);

        //holder.reqItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.reqItemName.setText(transaction.getProducts().get(position).getName());
        holder.reqItemAE.setText(transaction.getCount());
        holder.reqItemPrice.setText(transaction.getPriceTotal());
        holder.reqItemDate.setText(transaction.getDate());


        return convertView;
    }

    class CustomViewHolder { //restitem_reqdit.xml 아이디들
        ImageView reqItemImg;
        TextView reqItemName,reqItemAE, reqItemPrice, reqItemDate;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Transaction tr) {
        list.add(tr);
    }
}
