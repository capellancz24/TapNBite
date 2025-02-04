package com.example.tapnbite_original.AdapterClass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tapnbite_original.ModelClass.FoodProductClass;
import com.example.tapnbite_original.R;

import java.util.ArrayList;

public class FoodProductAdapter extends ArrayAdapter<FoodProductClass> {
    public FoodProductAdapter(@NonNull Context context, ArrayList<FoodProductClass> dataArrayList) {
        super(context, R.layout.viewholder_foodproduct, dataArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        FoodProductClass foodProductClass = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.viewholder_foodproduct, parent, false);
        }

        TextView foodName = view.findViewById(R.id.tvFoodName);
        TextView foodCategory = view.findViewById(R.id.tvFoodCategory);
        TextView pelletPrice = view.findViewById(R.id.tvPelletPrice);
        TextView stockStatus = view.findViewById(R.id.tvStockStatus);

        foodName.setText(foodProductClass.getName());
        foodCategory.setText(foodProductClass.getCategory());
        pelletPrice.setText(foodProductClass.getPrice());
        stockStatus.setText(foodProductClass.getStockStatus());

        switch (foodProductClass.getStockStatus()) {
            case "Available":
                stockStatus.setTextColor(Color.rgb(30, 224, 140)); // Green for Available
                break;
            case "Low Stock":
                stockStatus.setTextColor(Color.rgb(246, 141, 43)); // Orange for Low Stock
                break;
            case "Out Of Stock":
                stockStatus.setTextColor(Color.rgb(255, 7, 7)); // Red for Out Of Stock
                break;
            default:
                stockStatus.setTextColor(Color.BLACK); // Default color if status is unknown
                break;
        }

        return view;
    }
}
