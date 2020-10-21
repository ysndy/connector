package com.example.connector.doyeon.lib;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;

import java.util.ArrayList;

public class RequestAdapter extends BaseAdapter {

    // 상품선택리스트 어댑터
    private ArrayList<Product> list;
    private TextView priceTotalTv;
    private TextView selectedCountTv;
    private Product sp;
    //private ArrayList<Product> selectedProducts;

    public RequestAdapter(ArrayList<Product> list, TextView priceTotalTv, TextView selectedCountTv) {
        this.list = list;
        this.priceTotalTv = priceTotalTv;
        this.selectedCountTv = selectedCountTv;
        //selectedProducts = new ArrayList<>();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requestproductlist, null, false);

            //인플레이팅
            holder = new CustomViewHolder();
            holder.productName = (TextView) convertView.findViewById(R.id.productNameTV);
            holder.productFrom = (TextView) convertView.findViewById(R.id.productFromTV);
            holder.productPrice = (TextView) convertView.findViewById(R.id.productPriceTV);
            holder.productCountTV = (TextView) convertView.findViewById(R.id.countEditText);
            holder.downCountBtn = (Button) convertView.findViewById(R.id.downCount);
            holder.upCountBtn = (Button) convertView.findViewById(R.id.upCount);
            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        sp = list.get(position);

        //리스너 장착
        //업다운 이벤트 장착
        holder.downCountBtn.setOnClickListener(new CountEvent(holder.downCountBtn, holder.productCountTV, position));
        holder.upCountBtn.setOnClickListener(new CountEvent(holder.upCountBtn, holder.productCountTV, position));

        holder.productName.setText(sp.getName());
        holder.productFrom.setText(sp.getFrom());
        holder.productPrice.setText(sp.getPrice().toString());

        return convertView;
    }

    //public ArrayList<Product> getSelectedProducts() {
       // return selectedProducts;
    //}

    class CustomViewHolder {
        ImageView profileImg;
        TextView productFrom;
        TextView productName;
        TextView productPrice;
        TextView productCountTV;
        Button upCountBtn, downCountBtn;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Product sp) {
        list.add(sp);
    }

    class CountEvent implements Button.OnClickListener {

        Button countBtn;
        TextView countTV;
        int position;

        CountEvent(Button countBtn, TextView countTV, int position) {
            this.countBtn = countBtn;
            this.countTV = countTV;
            this.position = position;
        }

        @Override
        public void onClick(View view) {

            int count = Integer.parseInt(countTV.getText().toString());
            int counted = 0;

            if (countBtn.getText().toString().equals("+")) {
                counted = count + 1;
                Log.d("asd", "counted: "+counted);

            } else if (countBtn.getText().toString().equals("-") && count != 0) {
                counted = count - 1;
                Log.d("asd", "counted: "+counted);

            } else { //0일 때 - 버튼을 누르면.
                Log.d("asd", "counted: "+counted);
            }
            countTV.setText(counted + "");
            list.get(position).setSelectedCount(counted);
            priceTotalTv.setText(getTotalPrice()+"");
            selectedCountTv.setText(getProductCount()+"");

        }

    }

    public int getTotalPrice(){
        int total = 0;
        for(int i=0;i<list.size();i++){
            Product product = (Product)list.get(i);
            total += product.getPrice()*product.getSelectedCount();
        }
        return total;

    }

    public int getProductCount(){
        int total = 0;
        for(int i=0;i<list.size();i++){
            Product product = (Product)list.get(i);
            if(product.getSelectedCount()!=0) total++;
        }
        return total;

    }

}
