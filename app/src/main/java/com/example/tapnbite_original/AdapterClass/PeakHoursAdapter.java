package com.example.tapnbite_original.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tapnbite_original.ModelClass.PeakHoursClass;
import com.example.tapnbite_original.ModelClass.SalesRecordsClass;
import com.example.tapnbite_original.R;

import java.util.ArrayList;

public class PeakHoursAdapter extends ArrayAdapter<PeakHoursClass> {
    public PeakHoursAdapter(@NonNull Context context, ArrayList<PeakHoursClass> dataArrayList) {
        super(context, R.layout.viewholder_peak_hours, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        PeakHoursClass peakHoursClass = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_peak_hours, parent, false);
        }

        TextView hourStart = view.findViewById(R.id.tvHourStart);
        TextView hourEnd = view.findViewById(R.id.tvHourEnd);
        TextView breakName = view.findViewById(R.id.tvBreak);
        TextView orders = view.findViewById(R.id.tvOrders);

        hourStart.setText(peakHoursClass.getHourStart());
        hourEnd.setText(peakHoursClass.getHourEnd());
        breakName.setText(peakHoursClass.getBreakName());
        orders.setText(peakHoursClass.getOrders());



        return view;
    }
}
