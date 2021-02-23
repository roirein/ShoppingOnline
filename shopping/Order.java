package shopping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Order {

    private String number;
    private Date ordered;
    private Date shipped;
    private Address ship_to;
    private OrderStatus status;
    private float total;
    private Account account;
    private ArrayList<LineItem> lineItems;
    private ArrayList<Payment> payments;
    private int object_id;

    public Order(int object_id, String number, Date ordered, Date shipped, Address ship_to, float total) {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipped;
        this.ship_to = ship_to;
        this.status = OrderStatus.NEW;
        this.total = total;
        lineItems = new ArrayList<LineItem>();
        payments = new ArrayList<Payment>();
        this.object_id = object_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public Address getShip_to() {
        return ship_to;
    }

    public void setShip_to(Address ship_to) {
        this.ship_to = ship_to;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public void addLineItem(LineItem lineItem){
        if (!lineItems.contains(lineItem))
            lineItems.add(lineItem);
    }

    public void removeItem(LineItem lineItem){
        if (lineItems.contains(lineItem))
            lineItems.remove(lineItem);
    }

    public void addPayment(Payment payment){
        if (!payments.contains(payment))
            payments.add(payment);
    }

    public void removePayment(Payment payment){
        if (payments.contains(payment))
            payments.remove(payment);
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {

        String fields = "Order, " + "orderNumber:" + number + ", ordered:" + ordered + ", shipped:" + shipped + ", ship_to:" + ship_to + ", status:" + status.name() + ", total:" + total + "\n";
        String connected = "Connected to:" + account.getClass().getSimpleName();
        if (lineItems.size() > 0){
            String lines = "";
            for (LineItem li : lineItems){
                lines += li.getClass().getSimpleName() + " ";

            }
            connected += " " + lines;
        }
        if (payments.size() > 0){
            String pays = "";
            for (Payment p : payments){
                pays += p.getClass().getSimpleName() + " ";

            }
            connected += " " + pays;
        }
        return fields + connected;
    }
}
