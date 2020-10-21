package com.example.connector.doyeon.lib.request;

import android.util.Log;

import com.android.volley.Response;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.objects.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SupplierProductResponse implements Response.Listener {

    ArrayList<Product> products;
    public SupplierProductResponse() {
        products = new ArrayList<>();
    }

    @Override
    public void onResponse(Object response) {
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

    public ArrayList<Product> getProducts() {
        return products;
    }
}

