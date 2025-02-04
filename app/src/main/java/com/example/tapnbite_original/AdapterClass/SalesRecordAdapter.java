package com.example.tapnbite_original.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tapnbite_original.ModelClass.SalesRecordsClass;
import com.example.tapnbite_original.R;

import java.util.ArrayList;


public class SalesRecordAdapter extends ArrayAdapter<SalesRecordsClass> {
    public SalesRecordAdapter(@NonNull Context context, ArrayList<SalesRecordsClass> dataArrayList) {
        super(context, R.layout.viewholder_sales_records, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        SalesRecordsClass salesRecordClass = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_sales_records, parent, false);
        }

        TextView name = view.findViewById(R.id.tvFoodName);
        TextView quantity = view.findViewById(R.id.tvQuantity);
        TextView number = view.findViewById(R.id.tvNumber);

        name.setText(salesRecordClass.getName());
        quantity.setText(salesRecordClass.getQuantity());
        number.setText(salesRecordClass.getNumber());

        return view;
    }
}
