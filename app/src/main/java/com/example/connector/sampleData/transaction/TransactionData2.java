package com.example.connector.sampleData.transaction;

import com.example.connector.doyeon.lib.TranState;

public interface TransactionData2 {

    String supply = "나는거래처";
    String name = "원두";
    Integer num = 40;
    Integer price = 3000;
    String imageUrl = null;
    String date = "2020-06-19";
    String state = TranState.ARRAY[TranState.INDEX_SCHEDULE];

}
