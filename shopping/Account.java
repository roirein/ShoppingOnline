package shopping;

import java.util.ArrayList;
import java.util.Date;

public class Account {

    protected String accountId;
    protected String billingAddress;
    protected boolean isClosed;
    protected Date open;
    protected Date closed;
    protected int balance;
    protected ShoppingCart shoppingCart;
    protected ArrayList<Order> orders;
    protected ArrayList<Payment> payments;
    protected Customer customer;
    protected int object_id;

    public Account(int object_id, String accountId, String billingAddress,int balance) {
        this.object_id = object_id;
        this.balance = balance;
        this.accountId = accountId;
        this.billingAddress = billingAddress;
        this.isClosed = false;
        this.open = new Date();
        this.orders = new ArrayList<>();
        this.payments = new ArrayList<>();

    }

    public Customer getCustomer() {
        return customer;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Date getOpen() {
        return open;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        String fields = "Account, id:" + accountId + ", Billing Address:" + billingAddress + ", Is Closed:" + isClosed + ", Opened:" + open
                + ", Closed:" + closed + ", Balance:" + balance + "\n";
        String connected = "Connected to:" + customer.getClass().getSimpleName() + ", " + shoppingCart.getClass().getSimpleName();
        if (orders.size() > 0){
            String ords = "";
            for (Order o : orders){
                ords += o.getClass().getSimpleName() + " ";

            }
            connected += " " + ords;
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

    public void addPayment(Payment payment){
        if (!payments.contains(payment))
            payments.add(payment);
    }

    public void addOrder(Order order){
        if (!orders.contains(order))
            orders.add(order);
    }
}
