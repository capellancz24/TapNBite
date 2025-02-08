package com.example.tapnbite_original.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tapnbite_original.ModelClass.TransactionsClass;
import com.example.tapnbite_original.R;

import java.util.ArrayList;

public class TransactionsAdapter extends ArrayAdapter<TransactionsClass> {
    public TransactionsAdapter(@NonNull Context context,  ArrayList<TransactionsClass> dataArrayList) {
        super(context, R.layout.viewholder_transactions, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        TransactionsClass transactionsClass = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_transactions, parent, false);
        }

        TextView orderNum = view.findViewById(R.id.tvOrderNum);
        TextView canteenNum = view.findViewById(R.id.tvCanteenNum);
        TextView date = view.findViewById(R.id.tvDate);
        TextView number = view.findViewById(R.id.tvNumber);

        orderNum.setText(transactionsClass.getOrderNum());
        canteenNum.setText(transactionsClass.getCanteenNum());
        date.setText(transactionsClass.getDate());
        number.setText(transactionsClass.getNumber());

        return view;
    }
}
