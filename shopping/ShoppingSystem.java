package shopping;

import com.sun.corba.se.spi.ior.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ShoppingSystem {

    private HashMap<String, WebUser> webUsers; // String - (id) key
    private HashMap<Integer, Object> objects; // key - object id
    private HashMap<String, Supplier> suppliers; // key - supplier id
    private HashMap<String, Product> products; // key - product id

    private static int objectId = 1;
    private static int orderNumber = 1;
    private static int paymentNumber = 1;

    private static WebUser activeWebUser;

    public ShoppingSystem() {
        this.webUsers = new HashMap<>();
        this.objects = new HashMap<>();
        this.suppliers = new HashMap<>();
        this.products = new HashMap<>();

        ShoppingSystem.activeWebUser = null;

        // create default database

        this.addProduct("Bamba", "Bamba", "Moshe", "123");
        this.addProduct("Ramen", "Ramen", "Moshe", "123");

        this.addWebUser("Dani", "Dani123", "Israel", "Haifa", 100, "ShmuelHaNavi 3", "055425478512", "danibanani@post.bgu.ac.il", "ShmuelHaNavi 3", false);
        this.addWebUser("Dana", "Dana123", "Israel", "Ashdod", 100, "Rambam 8", "054832771", "danabanana@post.bgu.ac.il", "Rambam 8", true);

        activeWebUser = webUsers.get("Dana");
        addProductToAccount("Bamba", 8,5);
        activeWebUser = null;
    }

    /**
     *Creates a web user in the system and sets to it a new account.
     */
    public void addWebUser(String loginId,String password, String country, String city, int balance, String street,String phone,String email,String billingAddress, Boolean isPremiumAccount){

        WebUser webUser = new WebUser(objectId++, loginId, password);
        Customer customer = new Customer(objectId++, loginId, country, city, street, phone, email);

        Account account;
        if(isPremiumAccount)
            account = new PremiumAccount(objectId++, loginId, billingAddress, balance);
        else
            account = new Account(objectId++, loginId, billingAddress, balance);

        ShoppingCart shoppingCart = new ShoppingCart(objectId++, new Date());

        shoppingCart.setAccount(account);
        shoppingCart.setWebUser(webUser);
        account.setShoppingCart(shoppingCart);
        account.setCustomer(customer);
        customer.setAccount(account);
        customer.setWebUser(webUser);
        webUser.setCustomer(customer);
        webUser.setShoppingCart(shoppingCart);

        objects.put(customer.getObjectId(), customer);
        objects.put(account.getObjectId(), account);
        objects.put(shoppingCart.getObjectId(), shoppingCart);
        objects.put(webUser.getObjectId(), webUser);
        webUsers.put(loginId, webUser);

    }

    public void removeWebUser(String id){

        WebUser toRemove = (WebUser)webUsers.get(id);
        for(Order order : toRemove.getCustomer().getAccount().getOrders()){

            for (Payment payment : order.getPayments()){
                objects.remove(payment.getObjectId());
            }

            for (LineItem lineItem : order.getLineItems()){
                objects.remove(lineItem.getObjectId());
                lineItem.getProduct().getLineItems().remove(lineItem);
            }
        }

        for (Payment payment : toRemove.getCustomer().getAccount().getPayments()){
            objects.remove(payment.getObjectId());
        }


        objects.remove(toRemove.getCustomer().getAccount().getShoppingCart().getObjectId());
        if(toRemove.getCustomer().getAccount() instanceof PremiumAccount){
            for(Product product : ((PremiumAccount) toRemove.getCustomer().getAccount()).getProducts()){
                product.setSeller(null);
            }
        }
        objects.remove(toRemove.getCustomer().getAccount().getObjectId());
        objects.remove(toRemove.getCustomer().getObjectId());
        objects.remove(toRemove.getObjectId());
        webUsers.remove(id);

        if (activeWebUser!=null && activeWebUser.getLoginId().equals(id)){
            activeWebUser = null;
        }

    }

    public Boolean verifyLogin(String loginId, String password){

        if (webUsers.get(loginId).getPassword().equals(password)){

            webUsers.get(loginId).setState(UserState.ACTIVE);
            activeWebUser = webUsers.get(loginId);
            return true;
        }

        return false;
    }

    public String getActiveUser(){

        if (activeWebUser != null){
            return activeWebUser.getLoginId();
        }
        return "";
    }


    public void logOut(String Id){

        webUsers.get(Id).setState(UserState.NEW);
        activeWebUser = null;
    }

    public List<Product> showSellerProduct(String sellerId){

        Account seller = webUsers.get(sellerId).getCustomer().getAccount();

        if(seller instanceof PremiumAccount){
            return ((PremiumAccount) seller).getProducts();
        }

        return null;
    }

    public void addToShoppingCart(Product product, int quantity){

        LineItem lineItem = new LineItem(objectId++, quantity);

        lineItem.setShoppingCart(activeWebUser.getShoppingCart());
        activeWebUser.getShoppingCart().addLineItem(lineItem);

        product.addLineItem(lineItem);
        lineItem.setProduct(product);

        objects.put(lineItem.getObjectId(), lineItem);
    }

    public void updateShoppingCartDate() {

        activeWebUser.getShoppingCart().setCreated(new Date());
    }


    public Order getLastOrder(){

        List<Order> orders = activeWebUser.getCustomer().getAccount().getOrders();
        return orders.get(orders.size() - 1);
    }

    public void addProductToAccount(String productName, int quantity, int price){
        Product product = null;

        for(Product productIndex : products.values() ){
            if(productName.equals(productIndex.getName())){
                product = productIndex;
            }
        }

        Account seller = activeWebUser.getCustomer().getAccount();

        if(seller instanceof PremiumAccount)
            ((PremiumAccount) seller).addProduct(product, quantity, price);
    }

    public void addProduct(String productName, String productId, String supplierName, String supplierId){

        Supplier supplier;
        if (!suppliers.containsKey(supplierId)) {
            supplier = new Supplier(objectId++, supplierId, supplierName);
            suppliers.put(supplierId, supplier);
            objects.put(supplier.getObjectId(), supplier);
        }
        else
            supplier = suppliers.get(supplierId);

        Product product = new Product(objectId++, productId, productName);

        supplier.addProduct(product);
        product.setSupplier(supplier);

        objects.put(product.getObjectId(), product);
        products.put(productId, product);
    }

    public void showObjectId(int objectId){

        System.out.println(objects.get(objectId).toString());
    }

    public void showAllObjects(){

        for (Integer objectId: objects.keySet()) {
            String className = objects.get(objectId).getClass().getSimpleName();
            if(objects.get(objectId) instanceof Product){
                System.out.println(className + " " + objectId + " - " + ((Product)objects.get(objectId)).getName());
            }
            else if(objects.get(objectId) instanceof WebUser){
                System.out.println(className + " " + objectId + " - " + ((WebUser)objects.get(objectId)).getLoginId());
            }
            else if(objects.get(objectId) instanceof Account){
                System.out.println(className + " " + objectId + " - " + ((Account)objects.get(objectId)).getAccountId());
            }
            else if(objects.get(objectId) instanceof Supplier){
                System.out.println(className + " " + objectId + " - " + ((Supplier)objects.get(objectId)).getName());
            }
            else{
                System.out.println(className + " " + objectId);
            }
        }
    }

    public void makeOrder(Boolean isDelayed, int numOfPayments){

        Order order = new Order(objectId++, String.valueOf(orderNumber++), new Date(), new Date(), activeWebUser.getCustomer().getAddress(), 0);

        float total = 0;
        for(LineItem lineItem : activeWebUser.getShoppingCart().getLineItems()){

            total += lineItem.getPrice();
            order.addLineItem(lineItem);
            lineItem.getProduct().setInStock(lineItem.getProduct().getInStock() - lineItem.getQuantity());
        }
        order.setTotal(total);

        for(int i = 0; i < numOfPayments; i++) {

            Payment payment;

            if(isDelayed)
                payment = new DelayedPayment(objectId++,String.valueOf(paymentNumber++), new Date(),total/numOfPayments, "delayed payment", new Date());
            else
                payment = new ImmediatePayment(objectId++,String.valueOf(paymentNumber++), new Date(),total/numOfPayments, "immediate payment", false);

            order.addPayment(payment);
            payment.setOrder(order);

            activeWebUser.getCustomer().getAccount().addPayment(payment);
            payment.setAccount(activeWebUser.getCustomer().getAccount());

            objects.put(payment.getObjectId(), payment);
        }

        activeWebUser.getCustomer().getAccount().setBalance(activeWebUser.getCustomer().getAccount().getBalance() - (int)total);
        activeWebUser.getCustomer().getAccount().addOrder(order);
        order.setAccount(activeWebUser.getCustomer().getAccount());
        objects.put(order.getObjectId(), order);

        activeWebUser.getShoppingCart().getLineItems().clear();

    }

    public void DeleteProduct(String productNameDelete) {
        Product product = null;

        for(Product productIndex : products.values() ){
            if(productNameDelete.equals(productIndex.getName())){
                product = productIndex;
            }
        }

        for(Supplier supplier : suppliers.values()){
            if ((supplier.getProducts()).contains(product)){
                (supplier.getProducts()).remove(product);
            }
        }

        for(WebUser webUser : webUsers.values()){
            for(LineItem lineItem :(webUser.getShoppingCart().getLineItems())){
                if (lineItem.getProduct().equals(product))
                (webUser.getShoppingCart().getLineItems()).remove(lineItem);
            }
        }


        for(WebUser webUser : webUsers.values()){
            for(Order order :(webUser.getCustomer().getAccount().getOrders())){
                for(LineItem lineItem:order.getLineItems()){
                    if(lineItem.getProduct().equals(product)){
                        order.getLineItems().remove(lineItem);
                    }
                }
            }
        }

       for(LineItem lineItem: product.getLineItems()) {
           objects.remove(lineItem.getObjectId());
       }
       if(product.getSeller() != null){
           ArrayList<Product> products1 = product.getSeller().getProducts();
           products1.remove(product);
       }
       objects.remove(product.getObjectId());

    }
}
