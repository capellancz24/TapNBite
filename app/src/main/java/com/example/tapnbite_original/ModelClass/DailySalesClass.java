package com.example.tapnbite_original.ModelClass;

public class DailySalesClass {
    private String date, salesC1, salesC2;;
    public DailySalesClass(String date, String salesC1, String salesC2) {
        this.date = date;
        this.salesC1 = salesC1;
        this.salesC2 = salesC2;
    }

    public String getDate() {
        return date;
    }

    public String getSalesC1() {
        return salesC1;
    }

    public String getSalesC2() {
        return salesC2;
    }
}
