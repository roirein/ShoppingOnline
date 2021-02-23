package shopping;

import java.util.ArrayList;

public class Supplier {

    private String id;
    private String name;
    private ArrayList<Product> products;
    private int object_id;

    public Supplier(int object_id, String id, String name) {
        this.id = id;
        this.name = name;
        this. products = new ArrayList<Product>();
        this.object_id = object_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProduct(Product product){
        if (!products.contains(product)){
            products.add(product);
        }
    }

    public void removeProduct(Product product){
        if (products.contains(product)){
            products.remove(product);
        }
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    public String toString(){
        String fields = "Supplier, id:" + id + ", Name:" + name +"\n";
        String connected = "Connected to:";
        if (products.size() > 0){
            String prods = "";
            for (Product p : products){
                prods += p.getClass().getSimpleName() + ",";

            }
            connected += ", " + prods;
        }
        return fields + connected;
    }
}
