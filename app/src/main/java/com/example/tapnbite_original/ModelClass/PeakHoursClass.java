package com.example.tapnbite_original.ModelClass;

public class PeakHoursClass {

    private String hourStart, hourEnd, breakName, orders;

    public PeakHoursClass(String hourStart, String hourEnd, String breakName, String orders) {
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
        this.breakName = breakName;
        this.orders = orders;
    }
    public String getHourStart() {
        return hourStart;
    }

    public String getHourEnd() {
        return hourEnd;
    }

    public String getBreakName() {
        return breakName;
    }

    public String getOrders() {
        return orders;
    }


}
