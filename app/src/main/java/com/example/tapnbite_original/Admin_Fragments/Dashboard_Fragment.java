package com.example.tapnbite_original.Admin_Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tapnbite_original.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Dashboard_Fragment extends Fragment {

    BarChart bc;
    PieChart pc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        bc = view.findViewById(R.id.barchart);
        pc = view.findViewById(R.id.piechart);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            float value = (float) (i * 10.0);
            BarEntry bar = new BarEntry(i, value);
            PieEntry pie = new PieEntry(i, value);

            barEntries.add(bar);
            pieEntries.add(pie);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Canteen");
        barDataSet.setColors(ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS));
        barDataSet.setDrawValues(false);

        bc.setData(new BarData(barDataSet));
        bc.animateY(5000);
        bc.getDescription().setText("Canteen Sales");
        bc.getDescription().setTextColor(Color.DKGRAY);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Foods");
        pieDataSet.setColors(ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS));
        pc.setData(new PieData(pieDataSet));
        pc.animateXY(5000,5000);
        pc.getDescription().setEnabled(false);


        return view;
    }
}