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

    // 상품선택리스트 어댑터
    private static int priceTotal = 0;
    private static int productCount = 0;
    private static int preNum = 1;
    private static int preNum_input = 1;
    private ArrayList<Product> list;
    private TextView priceTotalTv;
    private TextView selectedCountTv;
    private Product sp;
    private ArrayList<Product> selectedProducts;

    public void fieldClear(){
        priceTotal = 0;
        productCount = 0;
        preNum = 1;
        preNum_input = 1;
    }

    public RequestAdapter(ArrayList<Product> list, TextView priceTotalTv, TextView selectedCountTv){
        this.list = list;
        this.priceTotalTv = priceTotalTv;
        this.selectedCountTv = selectedCountTv;
        selectedProducts = new ArrayList<>();
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
            holder.productCountEt = (EditText) convertView.findViewById(R.id.countEditText);

            convertView.setTag(holder);
        } else {
            holder = (CustomViewHolder) convertView.getTag();
        }

        sp = list.get(position);

        CheckBox cb = (CheckBox) convertView.findViewById(R.id.productCheckBox);

        //리스너 장착
        // 체크박스가 선택되었을 때 이벤트 처리
        cb.setOnCheckedChangeListener(new checkEvent(holder.productCountEt, sp.getPrice(), sp));

        //숫자만 입력 예외처리
        holder.productCountEt.addTextChangedListener(new ExceptionET(holder.productCountEt));

        //입력창이 활성화/비활성화되었을 때
        holder.productCountEt.setOnFocusChangeListener(new ChangeET(holder.productCountEt, sp.getPrice()));

        //키패드에서 완료/다음버튼을 눌렀을 때
        holder.productCountEt.setOnEditorActionListener(new OnEditorAction(holder.productCountEt));

        holder.productName.setText(sp.getName());
        holder.productFrom.setText(sp.getFrom());
        holder.productPrice.setText(sp.getPrice().toString());

        return convertView;
    }

    public ArrayList<Product> getSelectedProducts() {
        return selectedProducts;
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

    //체크박스를 체크/해제했을 때 이벤트 처리
    class checkEvent implements CompoundButton.OnCheckedChangeListener {

        Product selectedProduct;
        EditText editText;
        int price;

        checkEvent(EditText editText, int price, Product selectedProduct){
            this.selectedProduct = selectedProduct;
            this.editText = editText;
            this.price = price;
        }
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(isChecked){ // 체크되었을 때
                //입력창 활성화 처리
                editText.setBackgroundColor(ContextCompat.getColor(editText.getContext(), R.color.invisible));
                editText.setClickable(true);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);

                //총금액, 수량 계산
                priceTotal += (price * Integer.parseInt(editText.getText().toString()));
                productCount++;

                //선택된 상품 ArrayList에 추가
                selectedProducts.add(selectedProduct);
                selectedProduct.setSelectedCount(Integer.parseInt(editText.getText().toString()));

            } else { // 체크 해제되었을 때
                //입력창 비활성화 처리
                editText.setClickable(false);
                editText.setFocusable(false);
                editText.setBackgroundColor(ContextCompat.getColor(editText.getContext(), R.color.false1));
                //총금액, 수량 계산
                priceTotal -= (price * Integer.parseInt(editText.getText().toString()));
                productCount--;
                //선택된 상품에서 삭제
                selectedProducts.remove(selectedProduct);

            }

            //상품 수량, 총금액 세팅
            priceTotalTv.setText(""+priceTotal);
            selectedCountTv.setText(productCount+"");
        }
    }

    //숫자가 아닌 문자를 입력 받았을 때 예외처리
    class ExceptionET implements TextWatcher { //키 입력이 될 때마다 호출

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
        public void afterTextChanged(Editable s) { //입력된 후
            if(!et.getText().toString().equals("")) {//아무것도 입력되지 않았을 경우 제외
                try {
                    preNum_input = Integer.parseInt(et.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(et.getContext(), "숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show();
                    et.setText(preNum_input);
                }
            }
        }
    }

    // 상품 수량을 입력받는 EditText가 활성화되었을 때 입력된 숫자를 비활성화되었을 때 총 금액에 더함
    class ChangeET implements View.OnFocusChangeListener {

        EditText et;
        int price;

        ChangeET(EditText et, int price){

            this.et = et;
            this.price = price;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(hasFocus){ // 입력창이 활성화되었을 때
                preNum_input = Integer.parseInt(et.getText().toString());
                preNum = Integer.parseInt(et.getText().toString());
            }else {// 입력창에 포커스가 제거되었을 때
                if(et.getText().toString().equals("")) et.setText("1"); //기본값 1 세팅 예외처리
                //금액 계산
                priceTotal -= price*preNum;
                priceTotal += price*Integer.parseInt(et.getText().toString());
                //각 상품수량 세팅
                sp.setSelectedCount(Integer.parseInt(et.getText().toString()));
                preNum = Integer.parseInt(et.getText().toString());
                priceTotalTv.setText(""+priceTotal);
            }
        }
    }

    //숫자를 입력하고 키패드의 완료버튼을 눌렀을 때 키패드를 내린다.
    class OnEditorAction implements TextView.OnEditorActionListener {

        EditText et;
        OnEditorAction(EditText et){
            this.et = et;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(actionId== EditorInfo.IME_ACTION_DONE || actionId== EditorInfo.IME_ACTION_NEXT) {
                et.clearFocus();
                InputMethodManager imm = (InputMethodManager) et.getContext().getSystemService(et.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                fieldClear();
            }
            return false;
        }
    }

}
