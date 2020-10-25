package com.example.connector.doyeon.objects;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.lib.request.SupplierProductRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Profile implements Parcelable {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

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

    public void insertProducts(Context context) {
        //서버 DB 상품테이블에서 id로 상품 찾고 products 세팅
        //임시데이터임
//        ArrayList<Product> products = new ArrayList<>();
//        Product product = new Product();
//        product.setCategory(ProductData1.category);
//        product.setFrom(ProductData1.from);
//        product.setName(ProductData1.name);
//        product.setPrice(ProductData1.price);
//        product.setImageUrl(ProductData1.imageUrl);
//        products.add(product);
//
//        Product product2 = new Product();
//        product2.setCategory(ProductData2.category);
//        product2.setFrom(ProductData2.from);
//        product2.setName(ProductData2.name);
//        product2.setPrice(ProductData2.price);
//        product2.setImageUrl(ProductData2.imageUrl);
//        products.add(product2);
//
//        setProducts(products);
        products = new ArrayList<>();
        Response.Listener rListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jResponse = new JSONArray(response);
                    Log.d("asd", "insertProducts - jResponse.length() = "+jResponse.length());
                    for (int i = 0; i < jResponse.length(); i++) {
                        JSONObject jso = jResponse.getJSONObject(i);

                        final Product product = new Product();
                        product.setCategory(jso.getString(IntentName.CATEGORY));
                        product.setFrom(jso.getString(IntentName.FROMTO));
                        product.setName(jso.getString(IntentName.NAME));
                        product.setPrice(jso.getInt(IntentName.PRICE));
                        product.setImageUrl(jso.getString(IntentName.IMG));
                        products.add(product);

                    }
                    //서버에서 받은 reponse JSONObject 객체의 newID 키의 값을 받아와서 확인
                    //Log.d("asd", "jResponse"+jResponse.);

                } catch (Exception e) {
                    Log.d("asd", "Profile.java - "+e.toString());
                }
            }
        };

        SupplierProductRequest supplierProductRequest = new SupplierProductRequest(id, rListener); //Request 처리 클래스
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(supplierProductRequest);
        Log.d("asd", "Profile_products.size = "+products.size());

    }

    public void insertFollows() {
        //서버 DB 팔로우테이블에서 id로 팔로워 찾고 follows 세팅
    }

    public Profile() {

    }

    public Profile(Parcel in) {
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
    }

    public String getSimpleInfo() {
        return simpleInfo;
    }

    public void setSimpleInfo(String simpleInfo) {
        this.simpleInfo = simpleInfo;
    }
}
