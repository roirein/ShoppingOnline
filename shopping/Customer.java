package shopping;

public class Customer {

    private String name;
    private String phone;
    private String email;
    private Address address;
    private WebUser webUser;
    private Account account;
    private int object_id;

    public Customer(int object_id, String name, String phone, String email, String country, String city, String street) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = new Address(street, city, country);
        this.object_id = object_id;
    }

    public WebUser getWebUser() {
        return webUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public int getObjectId() {
        return object_id;
    }

    public void setObjectId(int object_id) {
        this.object_id = object_id;
    }

    @Override
    public String toString() {
        String fields = "Customer, id:" + name + ", Phone:" + phone + ", Email:" +email + ", Address:" + address.toString() + "\n";
        String connected = "Connected to:" + account.getClass().getSimpleName();
        if (webUser != null){
            connected += ", " + webUser.getClass().getSimpleName();
        }
        return fields+connected;
    }
}

