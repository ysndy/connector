package com.example.connector.sampleData.supplierprofile;

import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public interface SupplierData7 {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

    String name = "중앙상회";
    String id = "ysndy";
    String pw = "1234";
    String location = "서울시 서대문구 홍은동";
    String introduce = "중앙상회입니다.";
    String callNumber = "010-7157-8105";
    String email = "ysndy@naver.com";
    String imageUrl = "";
    String simpleInfo = "중앙상회입니다.";
    String major = "한식";
    Double rating = 5.0;
    ArrayList<Product> products = null;
    ArrayList<Profile> follows = null;

}
