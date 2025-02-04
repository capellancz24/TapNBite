package com.example.tapnbite_original.AdapterClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tapnbite_original.ModelClass.TopProductPerformanceClass;
import com.example.tapnbite_original.R;

import java.util.ArrayList;

public class TopProductPerformanceAdapter extends ArrayAdapter<TopProductPerformanceClass> {

    public TopProductPerformanceAdapter(@NonNull Context context, ArrayList<TopProductPerformanceClass> dataArrayList) {
        super(context, R.layout.viewholder_top_product_performance, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        TopProductPerformanceClass topProductPerformanceClass = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_top_product_performance, parent, false);
        }

        TextView name = view.findViewById(R.id.tvFoodName);
        TextView percentSales = view.findViewById(R.id.tvPercentSales);
        TextView percentTrend = view.findViewById(R.id.tvPercentTrend);
        ImageView trending = view.findViewById(R.id.ivTrending);

        name.setText(topProductPerformanceClass.getName());
        percentSales.setText(topProductPerformanceClass.getPercentSales());
        percentTrend.setText(topProductPerformanceClass.getPercentTrend());

        // Set the trend icon
        int trendIcon = topProductPerformanceClass.getImageTrend();
        trending.setImageResource(trendIcon);

        // Determine the text color based on the trend icon
        if (trendIcon == R.drawable.ic_trendingdown) { // Check the resource ID for trending down
            percentTrend.setTextColor(getContext().getResources().getColor(R.color.red)); // Set text color to red
        } else if (trendIcon == R.drawable.ic_trendingup) { // Check the resource ID for trending up
            percentTrend.setTextColor(getContext().getResources().getColor(R.color.green)); // Set text color to green
        }

        return view;
    }
}
