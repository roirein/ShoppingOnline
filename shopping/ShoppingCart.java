package shopping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {

    private Date created;
    private WebUser webUser;
    private Account account;
    private ArrayList<LineItem> lineItems;
    private int object_id;

    public ShoppingCart(int object_id, Date created) {

        this.created = created;
        this.object_id = object_id;
        lineItems = new ArrayList<>();
    }
     public void addLineItemToCart(LineItem lineItem){
        if(!lineItems.contains(lineItem)){
            addLineItemToCart(lineItem);
        }
     }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem lineItem){

        this.lineItems.add(lineItem);
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        String fields = "Shopping Cart, Created:" + created + "\n";
        String Connected = "Connected to:" + webUser.getClass().getSimpleName() + ", " + account.getClass().getSimpleName();
        if (lineItems.size() > 0){
            String lines = "";
            for (LineItem li : lineItems){
                lines += li.getClass().getSimpleName() + ",";

            }
            Connected += ", " + lines;
        }
        return fields + Connected;
    }
}
