package com.example.connector.doyeon.activity.transaction.act4.transaction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.connector.R;
import com.example.connector.doyeon.objects.Product;
import com.example.connector.doyeon.objects.Transaction;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionApplicationForm#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionApplicationForm extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Product> products;
    private Transaction transaction;
    private TextView dateTv, resName, supName, supAddress, priceTotal;
    private ListView productListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TransactionApplicationForm() {
        // Required empty public constructor
    }

    public TransactionApplicationForm(ArrayList<Product> selectedProducts, Transaction transaction) {

        this.products = selectedProducts;
        this.transaction = transaction;

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionApplicationForm.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionApplicationForm newInstance(String param1, String param2) {
        TransactionApplicationForm fragment = new TransactionApplicationForm();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_transaction_application_form, container, false);
        dateTv = rootView.findViewById(R.id.transactionDateTV);
        resName = rootView.findViewById(R.id.restaurantNameTV);
        supName = rootView.findViewById(R.id.supplierNameTV);
        supAddress = rootView.findViewById(R.id.supplierAddressTV);
        priceTotal = rootView.findViewById(R.id.priceTotalTv);
        productListView = rootView.findViewById(R.id.productListView);
        final View header = getLayoutInflater().inflate(R.layout.listview_header_transactionapp, null, false);
        productListView.addHeaderView(header);
        ApplicationProductListAdapter adapter = new ApplicationProductListAdapter(products);
        productListView.setAdapter(adapter);

        //데이터 세팅
        dateTv.setText(transaction.getDate());
        resName.setText(transaction.getRestaurant().getName());
        supName.setText(transaction.getSupplier().getName());
        supAddress.setText(transaction.getSupplier().getLocation());
        priceTotal.setText(transaction.getPriceTotal()+"");

        return rootView;
    }
}
