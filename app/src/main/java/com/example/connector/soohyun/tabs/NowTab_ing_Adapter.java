package com.example.connector.soohyun.tabs;

import android.content.Context;
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
import com.example.connector.soohyun.restaurantpage.NowRequest;
import com.example.connector.soohyun.shared.Login;

import java.util.ArrayList;

public class NowTab_ing_Adapter extends BaseAdapter {

    private ArrayList<Transaction> list;
    private Context context;

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

        this.context = parent.getContext();
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fragnow2,null,false);

            holder = new NowTab_ing_Adapter.CustomViewHolder();
            holder.now2ItemImg = convertView.findViewById(R.id.now2ItemImg);//공급처대표이미지
            holder.now2ItemSupply = convertView.findViewById(R.id.now2ItemSupply);//공급처명
            holder.now2ItemReqDate = convertView.findViewById(R.id.now2ItemReqDate); //신청일
            holder.greenBtn = convertView.findViewById(R.id.greenBtn); //거래진행중 버튼

            convertView.setTag(holder);
        }
        else {
            holder = (NowTab_ing_Adapter.CustomViewHolder) convertView.getTag();
        }
        Transaction transaction = list.get(position);

        //holder.now2ItemImg
        holder.now2ItemSupply.setText(transaction.getSupplyName()); //공급처명 표시
        holder.now2ItemReqDate.setText(transaction.getDate()); // 신청일 표시


        holder.greenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //greenBtn = 거래진행중 버튼을 눌렀을 때
                Intent intent = new Intent(context, NowRequest.class); //거래진행중인 내역 화면으로 이동
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    public class CustomViewHolder {

        ImageView now2ItemImg;
        TextView now2ItemSupply, now2ItemReqDate;
        Button greenBtn;
    }

    public void addItem(Transaction transaction){
        list.add(transaction);
    }
}
