package main.scheduler.c195finalproject.data;

import main.scheduler.c195finalproject.list.CountryList;
import main.scheduler.c195finalproject.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The CountryQuery class provides methods to interact with the Countries table in the database.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class CountryQuery {

    /**
     * Retrieves all countries from the Countries table and populates the CountryList.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectAll() throws SQLException {
        String sql = "SELECT * FROM COUNTRIES";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Country_ID");
            String name = resultSet.getString("Country");

            Country country = new Country(id, name);
            CountryList.add(country);
        }
    }
}
