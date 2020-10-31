package com.example.connector.doyeon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.connector.R;

import java.util.ArrayList;

public class Filter_region extends Fragment {

    ListView siDo, guGun;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> arrayAdapter1;

    public Filter_region() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_region, container, false);

        siDo = v.findViewById(R.id.siDo);
        guGun = v.findViewById(R.id.guGun);

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list));

        siDo.setAdapter(arrayAdapter);

        initAddress();

        return v;
    }

    private void initAddress() {
        siDo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        listItem(1);
                        break;
                    case 2:
                        listItem(2);
                        break;
                    case 3:
                        listItem(3);
                        break;
                    case 4:
                        listItem(4);
                        break;
                    case 5:
                        listItem(5);
                        break;
                    case 6:
                        listItem(6);
                        break;
                    case 7:
                        listItem(7);
                        break;
                    case 8:
                        listItem(8);
                        break;
                    case 9:
                        listItem(9);
                        break;
                    case 10:
                        listItem(10);
                        break;
                    case 11:
                        listItem(11);
                        break;
                    case 12:
                        listItem(12);
                        break;
                    case 13:
                        listItem(13);
                        break;
                    case 14:
                        listItem(14);
                        break;
                    case 15:
                        listItem(15);
                        break;
                    case 16:
                        listItem(16);
                        break;
                    case 17:
                        listItem(17);
                        break;
                }
            }
        });
    }

    private void listItem(int x) {
        switch (x) {
            case 0:
                ArrayList<String> items = new ArrayList<>();
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items);
                guGun.setAdapter(arrayAdapter1);
                break;
            case 1:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_seoul));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 2:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_incheon));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 3:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_daegion));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 4:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_daegu));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 5:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_gwangju));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 6:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_busan));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 7:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_ulsan));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 8:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_sejong));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 9:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_gyeonggi));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 10:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_gangwon));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 11:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_chungbuk));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 12:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_chungnam));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 13:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_gyeongbuk));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 14:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_gyeongnam));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 15:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_jeonbuk));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 16:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_jeonnam));
                guGun.setAdapter(arrayAdapter1);
                break;
            case 17:
                arrayAdapter1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.region_list_jeju));
                guGun.setAdapter(arrayAdapter1);
                break;
        }
    }

    public String[][] getGugunArray() {
        String[][] gugunArray = new String[18][];
        gugunArray[0] = null;
        gugunArray[1] = getResources().getStringArray(R.array.region_list_seoul);
        gugunArray[2] = getResources().getStringArray(R.array.region_list_incheon);
        gugunArray[3] = getResources().getStringArray(R.array.region_list_daegion);
        gugunArray[4] = getResources().getStringArray(R.array.region_list_daegu);
        gugunArray[5] = getResources().getStringArray(R.array.region_list_gwangju);
        gugunArray[6] = getResources().getStringArray(R.array.region_list_busan);
        gugunArray[7] = getResources().getStringArray(R.array.region_list_ulsan);
        gugunArray[8] = getResources().getStringArray(R.array.region_list_sejong);
        gugunArray[9] = getResources().getStringArray(R.array.region_list_gyeonggi);
        gugunArray[10] = getResources().getStringArray(R.array.region_list_gangwon);
        gugunArray[11] = getResources().getStringArray(R.array.region_list_chungbuk);
        gugunArray[12] = getResources().getStringArray(R.array.region_list_chungnam);
        gugunArray[13] = getResources().getStringArray(R.array.region_list_gyeongbuk);
        gugunArray[14] = getResources().getStringArray(R.array.region_list_gyeongnam);
        gugunArray[15] = getResources().getStringArray(R.array.region_list_jeonbuk);
        gugunArray[16] = getResources().getStringArray(R.array.region_list_jeonnam);
        gugunArray[17] = getResources().getStringArray(R.array.region_list_jeju);
        return gugunArray;
    }

    public ListView getSiDo() {
        return siDo;
    }

    public void setSiDo(ListView siDo) {
        this.siDo = siDo;
    }

    public ListView getGuGun() {
        return guGun;
    }

    public void setGuGun(ListView guGun) {
        this.guGun = guGun;
    }
}