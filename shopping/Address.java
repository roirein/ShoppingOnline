package shopping;

public class Address {

    private String street;
    private String cityName;
    private String country;

    public Address(String street, String cityName, String country) {
        this.street = street;
        this.cityName = cityName;
        this.country = country;
    }

    /**
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return country +" "+ cityName +" "+ street;
    }
}


