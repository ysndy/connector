package com.example.connector.doyeon.lib;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
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

import org.w3c.dom.Text;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class RequestAdapter extends BaseAdapter {

    // 체크박스 선택 이벤트 만들기

    //
    private static int priceTotal = 0;
    private static int productCount = 0;
    private static int preNum = 1;
    private static int preNum_input = 1;
    private ArrayList<Product> list;
    private TextView priceTotalTv;
    private TextView selectedCountTv;
    private Product sp;

    public RequestAdapter(ArrayList<Product> list, TextView priceTotalTv, TextView selectedCountTv){
        this.list = list;
        this.priceTotalTv = priceTotalTv;
        this.selectedCountTv = selectedCountTv;
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

            holder = new CustomViewHolder();
            //holder.profileImg = (ImageView) convertView.findViewById(R.id.image);
            holder.productName = (TextView) convertView.findViewById(R.id.productNameTV);
            holder.productFrom = (TextView) convertView.findViewById(R.id.productFromTV);
            holder.productPrice = (TextView) convertView.findViewById(R.id.productPriceTV);
            holder.productCountEt = (EditText) convertView.findViewById(R.id.countEditText);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        sp = list.get(position);

        CheckBox cb = (CheckBox) convertView.findViewById(R.id.productCheckBox);
        //리스너 장착
        cb.setOnCheckedChangeListener(new checkEvent(holder.productCountEt, sp.getPrice()));
        holder.productCountEt.addTextChangedListener(new ExceptionET(holder.productCountEt));
        holder.productCountEt.setOnFocusChangeListener(new ChangeET(holder.productCountEt, sp.getPrice()));
        holder.productCountEt.setOnEditorActionListener(new OnEditorAction(holder.productCountEt));

        //holder.profileImg.setImageURI(Uri.parse(sp.getImageUrl()));
        holder.productName.setText(sp.getName());
        holder.productFrom.setText(sp.getFrom());
        holder.productPrice.setText(sp.getPrice().toString());

        return convertView;
    }

    class CustomViewHolder {
        ImageView profileImg;
        TextView productFrom;
        TextView productName;
        TextView productPrice;
        EditText productCountEt;
    }

    // MainActivity에서 Adapter에있는 ArrayList에 data를 추가시켜주는 함수
    public void addItem(Product sp) {
        list.add(sp);
    }

    class checkEvent implements CompoundButton.OnCheckedChangeListener {

        EditText editText;
        int price;

        checkEvent(EditText editText, int price){
            this.editText = editText;
            this.price = price;
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){
                editText.setBackgroundColor(ContextCompat.getColor(editText.getContext(), R.color.invisible));
                editText.setClickable(true);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                priceTotal += (price * Integer.parseInt(editText.getText().toString()));
                productCount++;

            } else {
                editText.setClickable(false);
                editText.setFocusable(false);
                editText.setBackgroundColor(ContextCompat.getColor(editText.getContext(), R.color.false1));
                priceTotal -= (price * Integer.parseInt(editText.getText().toString()));
                productCount--;

            }

            priceTotalTv.setText("총 "+priceTotal+" 원");
            selectedCountTv.setText(productCount+" 개 상품 선택");
        }
    }

    class ExceptionET implements TextWatcher {

        EditText et;
        ExceptionET(EditText et){
            this.et = et;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(!et.getText().toString().equals("")) {
                try {
                    preNum_input = Integer.parseInt(et.getText().toString());

                } catch (NumberFormatException e) {
                    Toast.makeText(et.getContext(), "숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show();
                    et.setText(preNum_input);
                }
            }
        }
    }

    class ChangeET implements View.OnFocusChangeListener {

        EditText et;
        int price;

        ChangeET(EditText et, int price){

            this.et = et;
            this.price = price;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){
                //선택하자마자 1X상품값 더함
                //포커스가 꺼지면 이전 숫자를 빼고 입력된 숫자를 더함
                preNum_input = Integer.parseInt(et.getText().toString());
                preNum = Integer.parseInt(et.getText().toString());
            }else {
                if(et.getText().toString().equals("")) et.setText("1");
                priceTotal -= price*preNum;
                priceTotal += price*Integer.parseInt(et.getText().toString());
                preNum = Integer.parseInt(et.getText().toString());
                priceTotalTv.setText("총 "+priceTotal+" 원");
            }
        }
    }

    class OnEditorAction implements TextView.OnEditorActionListener {

        EditText et;
        OnEditorAction(EditText et){
            this.et = et;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId== EditorInfo.IME_ACTION_DONE) {
                et.clearFocus();
                InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(et.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
            return false;
        }
    }

}
