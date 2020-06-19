package com.example.connector.sampleData;

import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public interface RestaurantData1 {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

    String name = "도연채소";
    String id = "ysndy";
    String pw = "1234";
    String location = "서울시 서대문구 홍은동";
    String introduce = "싱싱한 당근, 배추를 납품합니다.";
    String callNumber = "010-7157-8105";
    String email = "ysndy@naver.com";
    String imageUrl = "";
    String major = "";
    Double stars = 0.0;
    ArrayList<Product> products = null;
    ArrayList<Profile> follows = null;

}
