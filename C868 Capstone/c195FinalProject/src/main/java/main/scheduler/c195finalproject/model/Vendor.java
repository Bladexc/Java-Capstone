package main.scheduler.c195finalproject.model;

public class Vendor extends Customer {


    private String type;

    private String relationship;

    /**
     * Constructs a new Customer object with the specified details.
     *
     * @param id           the ID of the customer
     * @param name         the name of the customer
     * @param address      the address of the customer
     * @param postalCode   the postal code of the customer
     * @param phone        the phone number of the customer
     * @param country      the country of the customer
     * @param division     the division of the customer
     * @param type         the type of the customer
     * @param relationship the relationship of the customer to the business
     */
    public Vendor(int id, String name, String address, String postalCode, String phone, String country, String division, String type, String relationship) {
        super(id, name, address, postalCode, phone, country, division);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
