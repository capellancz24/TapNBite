package App_Classes;

public class OrderHistory {
    private String orderNumber;
    private String status;
    private String date;

    public OrderHistory(String orderNumber, String status, String date) {
        this.orderNumber = orderNumber;
        this.status = status;
        this.date = date;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
