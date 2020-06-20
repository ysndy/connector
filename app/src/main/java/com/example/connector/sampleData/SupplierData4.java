package com.example.connector.sampleData;

import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Profile;

import java.util.ArrayList;

public interface SupplierData4 {

    //1. 이름 2. 지역 3. 소개 4. 연락처 5. 이메일 6. 프로필사진 7. 주류 8. 상품 9. 팔로워 + 아이디, 비밀번호

    String name = "모범정육점";
    String id = "ysndy";
    String pw = "1234";
    String location = "서울시 서대문구 홍은동";
    String introduce = "꼬리 목심 사골 사태 안심 양지 채끝";
    String callNumber = "02-0001-00001";
    String email = "mobum@naver.com";
    String imageUrl = "";
    String simpleInfo = "최고급 고기";
    String major = "한식";
    Double rating = 3.9;
    ArrayList<Product> products = null;
    ArrayList<Profile> follows = null;

}
