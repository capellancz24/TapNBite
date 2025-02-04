package com.example.tapnbite_original.ModelClass;

import android.widget.ImageView;

public class TopProductPerformanceClass {
    private String name, percentSales, percentTrend;
    private int imageTrend;

    public TopProductPerformanceClass(String name, String percentSales, String percentTrend, int imageTrend) {
        this.name = name;
        this.percentSales = percentSales;
        this.percentTrend = percentTrend;
        this.imageTrend = imageTrend;
    }

    public String getName() {
        return name;
    }

    public String getPercentSales() {
        return percentSales;
    }

    public String getPercentTrend() {
        return percentTrend;
    }

    public int getImageTrend() {
        return imageTrend;
    }
}
