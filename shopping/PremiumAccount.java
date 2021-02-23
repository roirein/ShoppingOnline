package shopping;

import java.util.ArrayList;
import java.util.List;

public class PremiumAccount extends Account {
    private ArrayList<Product> products;

    public PremiumAccount(int object_id, String accountId, String billingAddress, int balance) {
        super(object_id, accountId, billingAddress,balance);
        products = new ArrayList<Product>();
    }

    public void addProduct(Product product, int quantity, int price){
        product.setPrice(price);
        product.setInStock(quantity);
        products.add(product);

    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        String fields = "Account, id:" + accountId + ", Billing Address:" + billingAddress + ", Is Closed:" + isClosed + ", Opened:" + open
                + ", Closed:" + closed + ", Balance:" + balance + "\n";
        String connected = "Connected to:" + customer.getClass().getSimpleName() + ", " + shoppingCart.getClass().getSimpleName();
        if (orders.size() > 0){
            String ords = "";
            for (Order o : orders){
                ords += o.getClass().getSimpleName() + ",";

            }
            connected += ", " + ords;
        }
        if (payments.size() > 0){
            String pays = "";
            for (Payment p : payments){
                pays += p.getClass().getSimpleName() + ",";

            }
            connected += ", " + pays;
        }
        if (products.size() > 0){
            String prod = "";
            for (Product p : products){
                prod += p.getClass().getSimpleName() + ",";

            }
            connected += ", " + prod;
        }

        return fields + connected;
    }
}
