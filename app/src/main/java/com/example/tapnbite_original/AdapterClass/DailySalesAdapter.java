package com.example.tapnbite_original.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tapnbite_original.ModelClass.DailySalesClass;
import com.example.tapnbite_original.R;

import java.util.ArrayList;

public class DailySalesAdapter extends ArrayAdapter<DailySalesClass> {
    public DailySalesAdapter(@NonNull Context context, ArrayList<DailySalesClass> dataArrayList) {
        super(context, R.layout.viewholder_daily_sales, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        DailySalesClass dailySalesClass = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_daily_sales, parent, false);
        }

        TextView date = view.findViewById(R.id.tvDate);
        TextView salesC1 = view.findViewById(R.id.tvSalesC1);
        TextView salesC2 = view.findViewById(R.id.tvSalesC2);

        date.setText(dailySalesClass.getDate());
        salesC1.setText(dailySalesClass.getSalesC1());
        salesC2.setText(dailySalesClass.getSalesC2());

        return view;
    }
}
