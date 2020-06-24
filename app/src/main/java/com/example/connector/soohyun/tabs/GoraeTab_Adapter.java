package com.example.connector.soohyun.tabs;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;
import com.example.connector.soohyun.restaurantpage.MyPage;
import com.example.connector.soohyun.shared.Signup;

import java.util.ArrayList;

public class GoraeTab_Adapter extends BaseAdapter {

    private ArrayList<Profile> list;
    private Context context;
    Dialog dialog;

    public GoraeTab_Adapter(ArrayList<Profile> list){
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
        GoraeTab_Adapter.CustomViewHolder holder;

        this.context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.restitem_fraggorae, null, false);

            holder = new GoraeTab_Adapter.CustomViewHolder();
            holder.goraeItemImg = convertView.findViewById(R.id.goraeItemImg);
            holder.goraeItemSupply = convertView.findViewById(R.id.goraeItemSupply);
            holder.goraeItemContact = convertView.findViewById(R.id.goraeItemContact);
            holder.goraeItemTotal = convertView.findViewById(R.id.goraeItemTotal);
            holder.giverStar = convertView.findViewById(R.id.giveStar);

            convertView.setTag(holder);
        } else {
            holder = (GoraeTab_Adapter.CustomViewHolder) convertView.getTag();
        }
        Profile profile = list.get(position);

        // holder.goraeItemImg.setText(product.getName());
        holder.goraeItemSupply.setText(profile.getName());
        holder.goraeItemContact.setText(profile.getCallNumber());
        holder.goraeItemTotal.setText(profile.getProducts().get(0).getPrice().toString());

        //별점등록
        holder.giverStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //'giverStar'버튼 = '별점주기'을 눌렀을 때
                RatingBar ratingBar;
                Button addStar;

                dialog = new Dialog(context);
                dialog.setContentView(R.layout.rest_gorae_givestar_dialog);//별점주는 창이 뜸
                ratingBar = dialog.findViewById(R.id.ratingBar);
                addStar = dialog.findViewById(R.id.addStar);

                addStar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) { // 다이어로그 창안의 'addStar 버튼' = '별점등록 버튼' 을 눌렀을 때
                        dialog.dismiss(); //별점입력 후 다이어로그창이 사라질수 있게
                        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                        dlg.setTitle("별점 등록 완료");
                        dlg.setMessage("별점이 등록되었습니다.");
                        dlg.setPositiveButton("확인",null);
                        dlg.show();
                    }
                });
                dialog.setCanceledOnTouchOutside(false);//무조건 별점 등록해주어야 닫힐 수 있게 함. 다른데눌렀을 때 종료 안됨.
                dialog.show();
            }
        });

        return convertView;
    }

    public class CustomViewHolder {
        ImageView goraeItemImg;
        TextView goraeItemSupply, goraeItemContact, goraeItemTotal;
        Button giverStar,addStar;
    }

    public void addItem(Profile profile){
        list.add(profile);
    }
}
