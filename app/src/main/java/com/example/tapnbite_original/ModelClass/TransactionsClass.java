package com.example.tapnbite_original.ModelClass;

import java.util.zip.ZipEntry;

public class TransactionsClass {
    private String orderNum, canteenNum, date, number;

    public TransactionsClass(String orderNum, String canteenNum, String date, String number) {
        this.orderNum = orderNum;
        this.canteenNum = canteenNum;
        this.date = date;
        this.number = number;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public String getCanteenNum() {
        return canteenNum;
    }

    public String getDate() {
        return date;
    }

    public String getNumber() {
        return number;
    }
}
