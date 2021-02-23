package shopping;

import java.util.Date;

public abstract class Payment  {
    protected String id;
    protected Date paid;
    protected float total;
    protected String details;
    protected Order order;
    protected Account account;
    protected int object_id;

    public Payment(int object_id, String id, Date paid, float total, String details) {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.object_id = object_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public abstract String toString();
}
