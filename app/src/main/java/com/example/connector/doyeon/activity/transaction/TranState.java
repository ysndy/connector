package com.example.connector.doyeon.activity.transaction;

public interface TranState {

    String ARRAY[] = {"전체", "거래예정", "거래진행중", "거래완료", "거래취소", "신청완료"};

    int INDEX_SCHEDULE = 0;
    int INDEX_PROGRESS = 1;
    int INDEX_COMPLETE = 2;
    int INDEX_CANCEL = 3;
    int INDEX_REQUEST = 4;

}
