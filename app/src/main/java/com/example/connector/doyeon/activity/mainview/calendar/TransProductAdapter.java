package com.example.connector.doyeon.activity.mainview.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TransProductAdapter extends BaseAdapter {
    public ArrayList<Product> getList() {
        return list;
    }

    public void setList(ArrayList<Product> list) {
        this.list = list;
    }

    // 공급처 프로필에서 보여질 상품 리스트 어댑터
    private ArrayList<Product> list;
    private ViewGroup parent;

    public TransProductAdapter(ArrayList<Product> list){
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
        final CustomViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trans_product, null, false);
            this.parent = parent;
            holder = new CustomViewHolder();
            //holder.profileImg = (ImageView) convertView.findViewById(R.id.image);
            holder.productName = (TextView) convertView.findViewById(R.id.productNameTV);
            holder.productPrice = (TextView) convertView.findViewById(R.id.productPriceTV);
            holder.favoriteBtn = (ImageButton) convertView.findViewById(R.id.favoriteBtn);
            holder.state = (TextView) convertView.findViewById(R.id.stateTv);
            convertView.setTag(holder);

        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        Product sp = list.get(position);

        //holder.profileImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.productName.setText(sp.getName());
        holder.productPrice.setText(sp.getPrice().toString());
        holder.state.setText(sp.getTransactionState());

        return convertView;
    }

    class CustomViewHolder {
        ImageView profileImg;
        TextView productName;
        TextView productPrice;
        TextView state;
        ImageButton favoriteBtn;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Product sp) {
        list.add(sp);
    }
}
