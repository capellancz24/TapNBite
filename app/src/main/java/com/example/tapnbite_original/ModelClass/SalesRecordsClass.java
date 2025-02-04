package com.example.tapnbite_original.ModelClass;

public class SalesRecordsClass {
    private String name, quantity, number;

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getNumber() {
        return number;
    }

    public SalesRecordsClass(String name, String quantity, String number) {
        this.name = name;
        this.quantity = quantity;
        this.number = number;
    }
}
