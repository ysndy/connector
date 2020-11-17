package com.example.connector.sampleData.transaction;

import com.example.connector.doyeon.dictionary.TranState;

public interface TransactionData1 {

    String supply = "안녕거래";
    String name = "우유";
    Integer num = 10;
    Integer price = 800;
    String imageUrl = null;
    String date = "2020-06-19";
    String state = TranState.ARRAY[TranState.INDEX_PROGRESS];

}
