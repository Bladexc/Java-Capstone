package main.scheduler.c195finalproject.model;

/**
 * The Customer class represents a customer entity in the application.
 * It stores various details of a customer such as ID, name, address, postal code, phone number, country, and division.
 */
public abstract class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private String country;
    private String division;

    /**
     * Constructs a new {@code Customer} object with the specified details.
     *
     * @param id         the ID of the customer
     * @param name       the name of the customer
     * @param address    the address of the customer
     * @param postalCode the postal code of the customer
     * @param phone      the phone number of the customer
     * @param country    the country of the customer
     * @param division   the division of the customer
     */
    public Customer(int id, String name, String address, String postalCode, String phone, String country, String division) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.division = division;
    }


    /**
     * Returns the ID of the customer.
     *
     * @return the ID of the customer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the customer.
     *
     * @param id the ID of the customer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the customer.
     *
     * @return the name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name the name of the customer
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the customer.
     *
     * @return the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address the address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the postal code of the customer.
     *
     * @return the postal code of the customer
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the customer.
     *
     * @param postalCode the postal code of the customer
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phone the phone number of the customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the country of the customer.
     *
     * @return the country of the customer
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the customer.
     *
     * @param country the country of the customer
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the division of the customer.
     *
     * @return the division of the customer
     */
    public String getDivision() {
        return division;
    }

    /**
     * Sets the division of the customer.
     *
     * @param division the division of the customer
     */
    public void setDivision(String division) {
        this.division = division;
    }


}
