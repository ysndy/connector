package com.example.connector.doyeon.lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
// 공급처 프로필에서 보여질 상품 리스트 어댑터
    private ArrayList<Product> list;

    public ProductAdapter(ArrayList<Product> list){
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
        CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null, false);

            holder = new CustomViewHolder();
            //holder.profileImg = (ImageView) convertView.findViewById(R.id.image);
            holder.productName = (TextView) convertView.findViewById(R.id.productNameTV);
            holder.productPrice = (TextView) convertView.findViewById(R.id.productPriceTV);


            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        Product sp = list.get(position);

        //holder.profileImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.productName.setText(sp.getName());
        holder.productPrice.setText(sp.getPrice().toString());

        return convertView;
    }

    class CustomViewHolder {
        ImageView profileImg;
        TextView productName;
        TextView productPrice;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Product sp) {
        list.add(sp);
    }
}
