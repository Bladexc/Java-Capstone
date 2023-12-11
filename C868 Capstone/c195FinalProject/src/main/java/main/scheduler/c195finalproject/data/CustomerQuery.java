package main.scheduler.c195finalproject.data;


import main.scheduler.c195finalproject.list.CustomerList;
import main.scheduler.c195finalproject.model.Client;
import main.scheduler.c195finalproject.model.Customer;
import main.scheduler.c195finalproject.model.Vendor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The CustomerQuery class provides methods to interact with the Customers table in the database.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class CustomerQuery {

    /**
     * Retrieves all customer records from the CUSTOMERS table along with additional information from related tables to populate our CustomerList
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectAll() throws SQLException {
        String sql = "SELECT CUSTOMER_ID, Customer_name, Address, Postal_Code, Phone, Division, Country, name, relationship " +
                "FROM CUSTOMERS " +
                "JOIN FIRST_LEVEL_DIVISIONS " +
                "ON CUSTOMERS.Division_ID = FIRST_LEVEL_DIVISIONS.Division_ID " +
                "JOIN COUNTRIES " +
                "ON FIRST_LEVEL_DIVISIONS.Country_ID = COUNTRIES.Country_ID " +
                "JOIN TYPES " +
                "ON CUSTOMERS.Type_ID = TYPES.Type_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Customer_ID");
            String name = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String country = resultSet.getString("Country");
            String division = resultSet.getString("Division");
            String typeName = resultSet.getString("name");
            String relationship = resultSet.getString("relationship");

            if (typeName.equals("Client")) {
                Customer customer = new Client(id, name, address, postalCode, phone, country, division, typeName, relationship);
                CustomerList.add(customer);
            }
            if (typeName.equals("Vendor")) {
                Customer customer = new Vendor(id, name, address, postalCode, phone, country, division, typeName, relationship);
                CustomerList.add(customer);
            }

            //to modify code, simply add if statement to either create a vendor customer object or a client customer object.
            //Customer customer = new Customer(id, name, address, postalCode, phone, country, division);
            //CustomerList.add(customer);
        }
    }

/*    Commenting original Software II Customer Query in case it must be referenced later.

       public static void selectAll() throws SQLException {
        String sql = "SELECT CUSTOMER_ID, Customer_name, Address, Postal_Code, Phone, Division, Country \n" +
                "FROM CUSTOMERS\n" +
                "JOIN FIRST_LEVEL_DIVISIONS \n" +
                "ON Customers.Division_ID = FIRST_LEVEL_DIVISIONS.Division_ID\n" +
                "JOIN COUNTRIES\n" +
                "ON FIRST_LEVEL_DIVISIONS.Country_ID = COUNTRIES.Country_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Customer_ID");
            String name = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String country = resultSet.getString("Country");
            String division = resultSet.getString("Division");

            //to modify code, simply add if statement to either create a vendor customer object or a client customer object.
            Customer customer = new Customer(id, name, address, postalCode, phone, country, division);


            CustomerList.add(customer);
        }
    }*/

    /**
     * Inserts a new customer record into the CUSTOMERS table.
     *
     * @param name the name of the customer
     * @param address the address of the customer
     * @param postal the postal code of the customer
     * @param phone the phone number of the customer
     * @param division the division ID of the customer
     *
     * @return the number of rows affected in the database
     *
     * @throws SQLException if a database access error occurs
     */
    public static int insert(String name, String address, String postal, String phone, int division, int type) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID, Type_ID) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postal);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, division);
        preparedStatement.setInt(6, type);

        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected;
    }

    /**
     * Updates an existing customer record in the CUSTOMERS table.
     *
     * @param customerId the ID of the customer to update
     * @param name the new name of the customer
     * @param phone the new phone number of the customer
     * @param address the new address of the customer
     * @param postal the new postal code of the customer
     * @param divisionId the new division ID of the customer
     *
     * @return the number of rows affected in the database
     *
     * @throws SQLException if a database access error occurs
     */
    public static int update(int customerId, String name, String phone, String address, String postal, int divisionId, int typeId) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?, Type_ID = ? WHERE CUSTOMER_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        //be careful of the order, I used the order the columns appear in the database for the query,
        //but for method declaration, the order of the fields on the customer screen is the order of the inputs.
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postal);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, divisionId);
        preparedStatement.setInt(6, typeId);
        preparedStatement.setInt(7, customerId);

        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected;
    }


    /**
     * Deletes a customer record from the CUSTOMERS table.
     *
     * @param customerId the ID of the customer to delete
     *
     * @return the number of rows affected in the database
     *
     * @throws SQLException if a database access error occurs
     */
    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerId);

        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;

    }

}
