package com.example.connector.jeongeun.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Transaction;
import com.example.connector.jeongeun.providerpage.Deal;
import com.example.connector.jeongeun.providerpage.Request;
import com.example.connector.sampleData.transaction.TransactionData1;
import com.example.connector.sampleData.transaction.TransactionData2;

import java.util.ArrayList;


public class MiniPagerAdapter extends FragmentPagerAdapter {

    int num;

    public MiniPagerAdapter(@NonNull FragmentManager fm, int numTab) {
        super(fm);
        this.num = numTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Now_request tab1 = new Now_request();
                return tab1;
            case 1:
                Now_deal tab2 = new Now_deal();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num;
    }

    // 공급자 마이페이지 현황 > 거래 현황
    public static class Now_deal extends Fragment {

        ListView now_deal_list;
        Button pr_now_dealBtn;
        ArrayList<Transaction> transactions;
        TextView pr_now_dealName, pr_now_dealDate;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_now_deal, container, false);

            now_deal_list = v.findViewById(R.id.now_deal_list);

            // 임시 데이터

            ArrayList<Transaction> transactions;
            transactions = new ArrayList<>();
            Transaction transaction1 = new Transaction();

            transaction1.setSupplyName(TransactionData1.supply);
            transaction1.setDate(TransactionData1.date);

            transactions.add(transaction1);

            Transaction transaction2 = new Transaction();

            transaction2.setSupplyName(TransactionData2.supply);
            transaction2.setDate(TransactionData2.date);

            transactions.add(transaction2);

            Now_deal_list now_deal_adapter = new Now_deal_list(transactions);
            now_deal_list.setAdapter(now_deal_adapter);

            return v;
        }
    }

    public static class Now_deal_list extends BaseAdapter {

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
            CustomViewHolder holder;
            this.parent = parent;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_now_deal_list, null, false);

                holder = new CustomViewHolder();
                //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
                holder.dealName = convertView.findViewById(R.id.pr_now_dealName);
                holder.dealDate = (TextView) convertView.findViewById(R.id.pr_now_dealDate);

                convertView.setTag(holder);
            } else {
                holder = (CustomViewHolder) convertView.getTag();
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

    // 공급자 마이페이지 현황 > 신청 현황
    public static class Now_request extends Fragment {

        Button pr_now_requestBtn;
        TextView pr_now_requestName, pr_now_requestDate;
        ListView now_request_list;
        ArrayList<Transaction> transactions;

        public Now_request() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_now_request, container, false);

            pr_now_requestBtn = v.findViewById(R.id.pr_now_requestBtn);
            now_request_list = v.findViewById(R.id.now_request_list);

            // 임시 데이터

            ArrayList<Transaction> transactions;
            transactions = new ArrayList<>();
            Transaction transaction1 = new Transaction();

            transaction1.setSupplyName(TransactionData1.supply);
            transaction1.setDate(TransactionData1.date);

            transactions.add(transaction1);

            Transaction transaction2 = new Transaction();

            transaction2.setSupplyName(TransactionData2.supply);
            transaction2.setDate(TransactionData2.date);

            transactions.add(transaction2);

            Now_request_list now_request_adapter = new Now_request_list(transactions);
            now_request_list.setAdapter(now_request_adapter);

            return v;
        }
    }

    public static class Now_request_list extends BaseAdapter {

        ArrayList<Transaction> list;
        private ViewGroup parent;

        public Now_request_list(ArrayList<Transaction> list) {
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
            CustomViewHolder holder;
            this.parent = parent;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pr_now_request_list, null, false);

                holder = new CustomViewHolder();
                //holder.proItemImg = (ImageView) convertView.findViewById(R.id.image);
                holder.resName = convertView.findViewById(R.id.pr_now_requestName);
                holder.requestDate = (TextView) convertView.findViewById(R.id.pr_now_requestDate);

                convertView.setTag(holder);
            } else {
                holder = (CustomViewHolder) convertView.getTag();
            }

            Transaction transaction = list.get(position);

            //holder.proItemImg.setImageURI(Uri.parse(sp.getImageUrl()));
            holder.resName.setText(transaction.getSupplyName());
            holder.requestDate.setText(transaction.getDate());

            //
            Button pr_now_requestBtn = convertView.findViewById(R.id.pr_now_requestBtn);
            pr_now_requestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(parent.getContext(), Request.class);
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
}
