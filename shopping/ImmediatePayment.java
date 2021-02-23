package shopping;

import java.util.Date;

public class ImmediatePayment extends Payment{

    private boolean phoneConfirmation;

    public ImmediatePayment(int object_id, String id, Date paid, float total, String details, boolean phoneConfirmation) {
        super(object_id, id, paid, total, details);
        this.phoneConfirmation = phoneConfirmation;
    }

    public boolean isPhoneConfirmation() {
        return phoneConfirmation;
    }

    public void setPhoneConfirmation(boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }

    @Override
    public String toString() {
        String fields = "DelayedPayment, id:" + id + ", Paid:" + paid + ", Total:" + total + ", Details:" + details + ", Phone Confirmation:" + phoneConfirmation+ "\n";
        String connected = "Connected to:" + account.getClass().getSimpleName() + ", " + order.getClass().getSimpleName();
        return fields+connected;
    }
}
