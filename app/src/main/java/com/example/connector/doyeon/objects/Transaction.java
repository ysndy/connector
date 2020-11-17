package com.example.connector.doyeon.objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Transaction implements Parcelable { // 거래 내용 저장

    // 1. 날짜 2. 거래공급처 3. 거래음식점 4. 상품
    private String date; //날짜
    private Profile restaurant; //외식업자
    private Profile supplier; //공급처
    private Integer count;
    private Integer priceTotal; //총금액
    private Product product; //선택상품
    private String providerName; //공급처이름
    private String providerID; //공급처 아이디

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    private String supplierID;

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    private String restaurantID;
    private String tranState; // 거래상태

    public Transaction(){

    }

    protected Transaction(Parcel in) {
        date = in.readString();
        restaurant = in.readParcelable(Profile.class.getClassLoader());
        supplier = in.readParcelable(Profile.class.getClassLoader());
        product = in.readParcelable(Product.class.getClassLoader());
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
        if (in.readByte() == 0) {
            priceTotal = null;
        } else {
            priceTotal = in.readInt();
        }
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Profile getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Profile restaurant) {
        this.restaurant = restaurant;
    }

    public Profile getSupplier() {
        return supplier;
    }

    public void setSupplier(Profile supplier) {
        this.supplier = supplier;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Integer priceTotal) {
        this.priceTotal = priceTotal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeParcelable(restaurant, flags);
        dest.writeParcelable(supplier, flags);
        dest.writeParcelable(product, flags);
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(count);
        }
        if (priceTotal == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(priceTotal);
        }
    }
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }
}
