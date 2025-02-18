package com.example.tapnbite_original.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.R;

import java.util.ArrayList;
import java.util.List;

import App_Classes.OrderHistorAdapter;
import App_Classes.OrderHistory;

public class OrderHistory_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderHistorAdapter ohAdapter;
    private List<OrderHistory> orderHistoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_orderhistory, container, false);

        recyclerView = view.findViewById(R.id.rvHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        orderHistoryList = new ArrayList<>();
        orderHistoryList.add(new OrderHistory("Order 1234", "Paid", "01-10-2023"));
        orderHistoryList.add(new OrderHistory("Order 143", "Paid", "01-11-2023"));
        orderHistoryList.add(new OrderHistory("Order 4000", "Paid", "01-12-2023"));
        orderHistoryList.add(new OrderHistory("Order 1028", "Unpaid", "01-13-2023"));
        orderHistoryList.add(new OrderHistory("Order 555", "Unpaid", "01-14-2023"));


        ohAdapter = new OrderHistorAdapter(orderHistoryList);
        recyclerView.setAdapter(ohAdapter);

        return view;
    }
}