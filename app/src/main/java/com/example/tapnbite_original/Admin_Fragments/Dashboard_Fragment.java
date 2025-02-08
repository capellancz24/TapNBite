package com.example.tapnbite_original.Admin_Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tapnbite_original.Admin.Analytics_Activity;
import com.example.tapnbite_original.Admin.Check_Activity;
import com.example.tapnbite_original.Admin.Transactions_Activity;
import com.example.tapnbite_original.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dashboard_Fragment extends Fragment {

    private Button check, analytics, users, transactions;
    private PieChart mostFoodSales;
    private BarChart canteenPelletSales;
    private List<String> xValues = Arrays.asList("C1","C2");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mostFoodSales = view.findViewById(R.id.pcMostSaleFood);
        canteenPelletSales = view.findViewById(R.id.lcCanteenPelletSales);
        check = view.findViewById(R.id.btnCheck);
        analytics = view.findViewById(R.id.btnAnalytics);
        users = view.findViewById(R.id.btnUsers);
        transactions = view.findViewById(R.id.btnTransactions);

        displayBarChart();
        displayPieChart();
        dashboardButtonClicked();




        return view;
    }

    private void dashboardButtonClicked(){
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Check_Activity.class);
                startActivity(intent);
            }
        });

        analytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Analytics_Activity.class);
                startActivity(intent);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Transactions_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void displayBarChart (){
        canteenPelletSales.getAxisRight().setDrawLabels(false);

        ArrayList <BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0,3));
        entries.add(new BarEntry(2,5));
        entries.add(new BarEntry(3,8));

        YAxis yAxis = canteenPelletSales.getAxisLeft();
        yAxis.setAxisMinimum(1);
        yAxis.setAxisMaximum(10);
        yAxis.setAxisLineWidth(1f);
        yAxis.setAxisLineColor(Color.rgb(177, 177, 177));
        yAxis.setLabelCount(10);
        yAxis.setGridColor(Color.rgb(177, 177, 177));

        XAxis xAxis = canteenPelletSales.getXAxis();
        xAxis.setAxisLineColor(Color.rgb(177, 177, 177));

        BarDataSet dataSet = new BarDataSet(entries, "Pellet Sales");
        dataSet.setColors(ColorTemplate.createColors(new int[]{Color.rgb(2, 92, 248), Color.rgb(244, 167, 157)}));
        BarData barData = new BarData(dataSet);

        canteenPelletSales.setData(barData);
        canteenPelletSales.getDescription().setEnabled(false);
        canteenPelletSales.invalidate();
        canteenPelletSales.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
        canteenPelletSales.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        canteenPelletSales.getXAxis().setGranularity(1f);
        canteenPelletSales.getXAxis().setGranularityEnabled(true);
    }
    private void displayPieChart(){
        ArrayList<PieEntry> entiers = new ArrayList<>();
        entiers.add(new PieEntry(80f,"Adobo"));
        entiers.add(new PieEntry(90f,"Lemonade"));
        entiers.add(new PieEntry(75f,"Rice"));

        PieDataSet pieDataSet = new PieDataSet(entiers, "Most Sale Food");
        pieDataSet.setColors(ColorTemplate.createColors(new int[]{Color.rgb(246, 141, 43), Color.rgb(2, 92, 248), Color.rgb(244, 167, 157)}));

        PieData pieData = new PieData(pieDataSet);

        mostFoodSales.setDrawEntryLabels(false);
        mostFoodSales.setData(pieData);

        mostFoodSales.getDescription().setEnabled(false);
        mostFoodSales.animateY(1000);
        mostFoodSales.invalidate();
    }
}