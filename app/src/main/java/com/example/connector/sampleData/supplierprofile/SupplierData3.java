package com.example.connector.sampleData.supplierprofile;

import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public interface SupplierData3 {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

    String name = "장우네밤가게";
    String id = "ysndy";
    String pw = "1234";
    String location = "서울시 은평구 응암동";
    String introduce = "10년 전통의 햇밤 전문점입니다.";
    String callNumber = "066-8921-5333";
    String email = "ysndy@naver.com";
    String imageUrl = "";
    String simpleInfo = "햇밤 전문점";
    String major = "한식";
    Double rating = 3.8;
    ArrayList<Product> products = null;
    ArrayList<Profile> follows = null;

}
