package com.example.tapnbite_original.ModelClass;

import androidx.recyclerview.widget.RecyclerView;

public class FoodProductClass{

    private String foodName, foodCategory, pelletPrice, stockStatus;

    public FoodProductClass(String foodName, String foodCategory, String pelletPrice, String stockStatus) {
        this.foodName = foodName;
        this.foodCategory = foodCategory;
        this.pelletPrice = pelletPrice;
        this.stockStatus = stockStatus;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public String getPelletPrice() {
        return pelletPrice;
    }

    public String getStockStatus() {
        return stockStatus;
    }
}
