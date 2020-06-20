package com.example.connector.sampleData;

import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public interface SupplierData2 {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

    String name = "유성청과";
    String id = "ysndy2";
    String pw = "1234";
    String location = "서울시 서대문구 북가좌동";
    String introduce = "계절별 과일과 한식 반찬재료를 취급합니다.";
    String callNumber = "500-2123-5999";
    String email = "ysndy@naver.com";
    String imageUrl = "";
    String simpleInfo = "과일 전문";
    String major = "한식";
    Double rating = 4.5;
    ArrayList<Product> products = null;
    ArrayList<Profile> follows = null;

}
