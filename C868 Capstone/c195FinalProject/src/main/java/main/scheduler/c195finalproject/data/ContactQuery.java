package main.scheduler.c195finalproject.data;

import main.scheduler.c195finalproject.list.ContactList;
import main.scheduler.c195finalproject.model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The ContactQuery class provides methods to interact with the Contacts table in the database.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class ContactQuery {

    /**
     * Retrieves all contacts from the Contacts table and populates the ContactList.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectAll() throws SQLException {
        String sql = "SELECT * FROM CONTACTS";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Contact_ID");
            String name = resultSet.getString("Contact_Name");

            Contact contact = new Contact(id, name);
            ContactList.add(contact);
        }
    }
}
