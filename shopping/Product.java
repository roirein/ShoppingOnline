package shopping;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String id;
    private String name;
    private Supplier supplier;
    private PremiumAccount seller;
    private List<LineItem> lineItems;
    private int price;
    private int inStock;
    private int object_id;

    public Product(int object_id, String id, String name) {
        this.id = id;
        this.name = name;
        this.lineItems = new ArrayList<>();
        this.object_id = object_id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public PremiumAccount getSeller() {
        return seller;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem lineItem) {

        if(lineItem != null){
            this.lineItems.add(lineItem);
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setSeller(PremiumAccount seller) {
        this.seller = seller;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        String fields = "Product, id:" + id + ", Name:" + name + ", inStock" + inStock + "\n";
        String Connected = "Connected to:" + supplier.getClass().getSimpleName();
        if (lineItems.size() > 0){
            String lines = "";
            for (LineItem li : lineItems){
                lines += li.getClass().getSimpleName() + " ";

            }
            Connected += " " + lines;
        }
        if (seller != null){
            Connected+= ", Seller:" + seller.getClass().getSimpleName();
        }
        return fields + Connected;
    }
}
