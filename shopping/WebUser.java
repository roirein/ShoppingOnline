package shopping;

public class WebUser {
    private String login_id;
    private String password;
    private UserState state;
    private Customer customer;
    private ShoppingCart shoppingCart;
    private int object_id;

    public WebUser(int object_id, String login_id, String password) {

        this.login_id = login_id;
        this.password = password;
        this.state = UserState.NEW;
        this.object_id = object_id;
    }

    public void addShoppingCart(ShoppingCart sc){

        if(shoppingCart == null){
            shoppingCart = sc;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public String getLoginId() {
        return login_id;
    }

    public void setLoginId(String login_id) {
        this.login_id = login_id;
    }

    /*public void deleteWebUser(){
        shoppingCart.deleteShoppingCart();
    }*/

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        String field = "WebUser, Login ID:" + login_id + ", Password:" + password + ", Status:" + state.name() +"\n";
        String connected = "Conncted to:" + customer.getClass().getSimpleName();
        if (shoppingCart != null){
            connected +=  ", " + shoppingCart.getClass().getSimpleName();
        }
        return field+connected;
    }
}
