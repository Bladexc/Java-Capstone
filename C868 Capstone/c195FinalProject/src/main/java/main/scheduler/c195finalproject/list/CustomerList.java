package main.scheduler.c195finalproject.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.scheduler.c195finalproject.model.Contact;
import main.scheduler.c195finalproject.model.Customer;

import java.util.Comparator;

/**
 * The {@code CustomerList} class represents a list of customers in the application.
 * It provides methods to add, update, and delete customers from the list.
 */
public class CustomerList {

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    private static ObservableList<String> allCustomerNames = FXCollections.observableArrayList();

    /**
     * Adds a new customer to the list.
     *
     * @param newCustomer the customer to add
     */
    public static void add(Customer newCustomer) {
        allCustomers.add(newCustomer);
        CustomerList.getAllCustomers().sort(Comparator.comparing(Customer::getId));
    }

    /**
     * Adds a new customer name to the list of customer names.
     *
     * @param name the customer name to add
     */
    public static void addCustomerName(String name) {
        allCustomerNames.add(name);
        CustomerList.getAllCustomerNames().sort(Comparator.comparing(String::toString));
    }

    /**
     * Updates an existing customer in the list.
     *
     * @param index            the index of the customer to update
     * @param selectedCustomer the updated customer
     */
    public static void update(int index, Customer selectedCustomer) {
        int count = -1;

        for (Customer allCustomers : CustomerList.getAllCustomers()) {
            count++;

            if (allCustomers.getId() == index ) { // for every single update, I may need to add index + 1 as the condition
                CustomerList.getAllCustomers().set(count, selectedCustomer);
            }
        }
    }

    /**
     * Deletes a customer from the list.
     *
     * @param selectedCustomer the customer to delete
     * @return {@code true} if the customer was successfully deleted, {@code false} otherwise
     */
    public static boolean delete(Customer selectedCustomer) {
        for (Customer allCustomers : CustomerList.getAllCustomers()) {
            if (selectedCustomer.equals(allCustomers)) {
                CustomerList.getAllCustomers().remove(selectedCustomer);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the list of all customers.
     *
     * @return the list of all customers
     */
    public static ObservableList<Customer> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Returns the list of all customer names.
     *
     * @return the list of all customer names.
     */
    public static ObservableList<String> getAllCustomerNames() {
        return allCustomerNames;
    }

    /**
     * Builds the list of customer names in the CustomerList.
     * This method retrieves all customers from the CustomerList,
     * extracts their names, and adds them to the list of customer names.
     */
    public static void buildCustomerNameList() {
        CustomerList.getAllCustomerNames().clear();

        for (int count = 0; count < CustomerList.getAllCustomers().size(); count++) {
            String name = CustomerList.getAllCustomers().get(count).getName();

            CustomerList.addCustomerName(name);
        }

    }

    /**
     * Looks up the customer ID based on the customer name.
     *
     * @param name the name of the customer
     * @return the ID of the customer if found, or 0 if not found
     */
    public static int lookupCustomerId(String name) {
        for (Customer allCustomers : CustomerList.getAllCustomers()) {
            if (allCustomers.getName().equals(name)) {
                return allCustomers.getId();
            }
        }
        return 0;
    }

    /**
     * Looks up the customer name based on the customer ID.
     *
     * @param id the ID of the customer
     * @return the name of the customer if found, or null if not found
     */
    public static String lookupCustomerName(int id) {
        for (Customer allCustomers : CustomerList.getAllCustomers()) {
            if (allCustomers.getId() == id) {
                return allCustomers.getName();
            }
        }
        return null;
    }

}
