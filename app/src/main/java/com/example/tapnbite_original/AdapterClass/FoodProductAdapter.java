package com.example.tapnbite_original.AdapterClass;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tapnbite_original.ModelClass.FoodProductClass;
import com.example.tapnbite_original.R;

import java.util.List;

public class FoodProductAdapter extends RecyclerView.Adapter<FoodProductAdapter.FoodItemViewHolder> {

    private List<FoodProductClass> foodItemList;
    private Context context;

    public FoodProductAdapter(Context context, List<FoodProductClass> foodItemList) {
        this.context = context;
        this.foodItemList = foodItemList;
    }

    public static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, price, stockStatus;
        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvFoodName);
            category = itemView.findViewById(R.id.tvFoodCategory);
            price = itemView.findViewById(R.id.tvPelletPrice);
            stockStatus = itemView.findViewById(R.id.tvStockStatus);
        }
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewholder_foodproduct, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodProductClass foodItem = foodItemList.get(position);
        holder.name.setText(foodItem.getFoodName());
        holder.category.setText(foodItem.getFoodCategory());
        holder.price.setText(foodItem.getPelletPrice());
        holder.stockStatus.setText(foodItem.getStockStatus());

        // Change background color based on stock status
        switch (foodItem.getStockStatus()) {
            case "Available":
                holder.stockStatus.setTextColor(context.getResources().getColor(R.color.green));// Change to your desired color
                break;
            case "Low Stock":
                holder.stockStatus.setTextColor(context.getResources().getColor(R.color.orange)); // Change to your desired color
                break;
            case "Out Of Stock":
                holder.stockStatus.setTextColor(context.getResources().getColor(R.color.red)); // Change to your desired color
                break;
            default:
                holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.text)); // Default color
                break;
        }
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }
}
