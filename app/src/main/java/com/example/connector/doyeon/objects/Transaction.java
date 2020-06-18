package com.example.connector.doyeon.objects;

import java.util.ArrayList;

public class Transaction { // 거래 내용 저장

    // 1. 날짜 2. 거래공급처 3. 거래음식점 4. 상품
    private String date;
    private Profile restaurant;
    private Profile supplier;
    private Integer count;
    private Integer priceTotal;
    private ArrayList<Product> products;

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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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
}
