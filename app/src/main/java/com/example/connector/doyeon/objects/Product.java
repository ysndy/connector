package com.example.connector.doyeon.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;//상품 코드
    private String name;//상품 이름
    private Integer price;//가격
    private String imageUrl;//이미지
    private String category;//카테고리
    private String from;//원산지
    private Profile supplier;//공급처
    private Integer selectedCount;//거래 시 선택수량
    private String supplyName; //공급처이름
    private String transactionState; //거래 상태

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    private String transactionDate; //거래일

    public Product() {
        selectedCount = 0;
    }


    protected Product(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readInt();
        }
        imageUrl = in.readString();
        category = in.readString();
        from = in.readString();
        supplier = in.readParcelable(Profile.class.getClassLoader());
        if (in.readByte() == 0) {
            selectedCount = null;
        } else {
            selectedCount = in.readInt();
        }
        supplyName = in.readString();
        //selectedCount = 0; 화면이 넘어가면 자동 실행됨
        code = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (price == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(price);
        }
        dest.writeString(imageUrl);
        dest.writeString(category);
        dest.writeString(from);
        dest.writeParcelable(supplier, flags);
        if (selectedCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(selectedCount);
        }
        dest.writeString(supplyName);
        dest.writeString(code);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(Integer selectedCount) {
        this.selectedCount = selectedCount;
    }

    public Profile getSupplier() {
        return supplier;
    }

    public void setSupplier(Profile supplier) {
        this.supplier = supplier;
    }


    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }
}
