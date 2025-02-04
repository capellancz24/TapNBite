package com.example.tapnbite_original.ModelClass;

public class FoodProductClass {
    private String name, category, price, stockStatus;

    public FoodProductClass(String name, String category, String price, String stockStatus) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockStatus = stockStatus;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return price;
    }

    public String getStockStatus() {
        return stockStatus;
    }
}
