package com.example.connector.doyeon.lib;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
// 공급처 프로필에서 보여질 상품 리스트 어댑터
    private ArrayList<Product> list;
    private Profile profile_res;

    public ProductAdapter(ArrayList<Product> list){
        this.list = list;
    }

    public ProductAdapter(ArrayList<Product> list, Profile profile_res){
        this.list = list;
        this.profile_res = profile_res;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null, false);

            holder = new CustomViewHolder();
            //holder.profileImg = (ImageView) convertView.findViewById(R.id.image);
            holder.productName = (TextView) convertView.findViewById(R.id.productNameTV);
            holder.productPrice = (TextView) convertView.findViewById(R.id.productPriceTV);
            holder.favoriteBtn = (ImageButton) convertView.findViewById(R.id.favoriteBtn);
            convertView.setTag(holder);

        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        Product sp = list.get(position);

        //holder.profileImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.productName.setText(sp.getName());
        holder.productPrice.setText(sp.getPrice().toString());
        holder.favoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.favoriteBtn.getImageAlpha()==R.drawable.ic_favorite_red_24dp){
                    holder.favoriteBtn.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                }else holder.favoriteBtn.setImageResource(R.drawable.ic_favorite_red_24dp);
            }
        });

        return convertView;
    }

    class CustomViewHolder {
        ImageView profileImg;
        TextView productName;
        TextView productPrice;
        ImageButton favoriteBtn;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Product sp) {
        list.add(sp);
    }
}
