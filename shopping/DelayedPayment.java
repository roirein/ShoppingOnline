package shopping;

import java.util.Date;

public class DelayedPayment extends Payment {

    private Date paymentDate;

    public DelayedPayment(int object_id, String id, Date paid, float total, String details, Date paymentDate) {
        super(object_id, id, paid, total, details);
        this.paymentDate = paymentDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        String fields = "DelayedPayment, id:" + id + ", Paid:" + paid + ", Total:" + total + ", Details:" + details + ", PaymentDate:" + paymentDate+ "\n";
        String connected = "Connected to:" + account.getClass().getSimpleName() + ", " + order.getClass().getSimpleName();
        return fields+connected;
    }
}
