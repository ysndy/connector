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
import com.example.connector.soohyun.restaurantpage.EditRequest;

import java.util.ArrayList;

public class NowTab_request_Adapter extends BaseAdapter {

    private ArrayList<Transaction> list;
    /*private Context context;*/
    private ViewGroup parent;

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
    public View getView(int position, View convertView, final ViewGroup parent) {
        NowTab_request_Adapter.CustomViewHolder holder;
        this.parent = parent;
        /*this.context = parent.getContext();*/

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fragnow1, null, false);

            holder = new NowTab_request_Adapter.CustomViewHolder();
            holder.now1ItemImg = convertView.findViewById(R.id.now1ItemImg);
            holder.now1ItemSupply = convertView.findViewById(R.id.now1ItemSupply);
            holder.now1ItemReqDate = convertView.findViewById(R.id.now1ItemReqDate);
            holder.yellBtn = convertView.findViewById(R.id.yellBtn);

            convertView.setTag(holder);
        }
        else{
            holder = (NowTab_request_Adapter.CustomViewHolder) convertView.getTag();
        }
        Transaction transaction = list.get(position);

        holder.now1ItemSupply.setText(transaction.getProviderName()); //신청한 공급처명 표시
        holder.now1ItemReqDate.setText(transaction.getDate()); //신청한 날짜 표시

        holder.yellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //yellBtn = 수정요청 버튼 눌렀을 때
                /*Intent intent = new Intent(context, EditRequest.class); //수정요청받은 화면으로 이동
                context.startActivity(intent);*/
                Intent intent = new Intent(parent.getContext(), EditRequest.class);
                parent.getContext().startActivity(intent);
            }
        });


        return convertView;
    }

    public class CustomViewHolder {
        ImageView now1ItemImg;
        TextView now1ItemSupply, now1ItemReqDate;
        Button yellBtn;
    }
    public void addItem(Transaction transaction){
        list.add(transaction);
    }
}
