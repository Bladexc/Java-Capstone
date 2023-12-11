package main.scheduler.c195finalproject.data;

import main.scheduler.c195finalproject.list.DivisionList;
import main.scheduler.c195finalproject.model.Division;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The DivisionQuery class provides methods for executing database queries related to divisions.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class DivisionQuery {

    /**
     * Retrieves all First_Level_Divisions from the CUSTOMERS table along with additional information from related tables to populate our DivisionList.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectAll() throws SQLException {
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("Division_ID");
            String name = resultSet.getString("Division");
            int countryId = resultSet.getInt("Country_ID");

            Division division = new Division(id, name, countryId);
            DivisionList.add(division);
        }
    }
}
