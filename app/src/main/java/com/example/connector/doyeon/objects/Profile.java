package com.example.connector.doyeon.objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Profile implements Parcelable {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

    private int loginCode;
    private String name;
    private String id;
    private String pw;
    private String location;
    private String introduce;
    private String callNumber;
    private String email;
    private String imageUrl;
    private String major; //주거래업종
    private String simpleInfo; //간략소개
    private Double rating;
    private ArrayList<Product> products;
    private ArrayList<Profile> follows;

    public void insertFollows() {
        //서버 DB 팔로우테이블에서 id로 팔로워 찾고 follows 세팅
    }

    public Profile() {

    }

    public Profile(@NotNull Parcel in) {

        loginCode = in.readInt();
        name = in.readString();
        id = in.readString();
        pw = in.readString();
        location = in.readString();
        introduce = in.readString();
        callNumber = in.readString();
        email = in.readString();
        imageUrl = in.readString();
        major = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        follows = in.createTypedArrayList(Profile.CREATOR);
        products = in.createTypedArrayList(Product.CREATOR);
        //insertProducts();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public float getRating() {
        return rating.floatValue();
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public ArrayList<Profile> getFollows() {
        return follows;
    }

    public void setFollows(ArrayList<Profile> follows) {
        this.follows = follows;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(loginCode);
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(pw);
        dest.writeString(location);
        dest.writeString(introduce);
        dest.writeString(callNumber);
        dest.writeString(email);
        dest.writeString(imageUrl);
        dest.writeString(major);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(rating);
        }
        dest.writeTypedList(follows);
        dest.writeTypedList(products);
    }

    public String getSimpleInfo() {
        return simpleInfo;
    }

    public void setSimpleInfo(String simpleInfo) {
        this.simpleInfo = simpleInfo;
    }

    public Integer getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(Integer loginCode) {
        this.loginCode = loginCode;
    }
}
