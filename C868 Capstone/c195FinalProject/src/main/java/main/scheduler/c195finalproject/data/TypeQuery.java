package main.scheduler.c195finalproject.data;

import main.scheduler.c195finalproject.list.CountryList;
import main.scheduler.c195finalproject.list.TypeList;
import main.scheduler.c195finalproject.model.Country;
import main.scheduler.c195finalproject.model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The TypeQuery class provides methods to interact with the Types table in the database.
 *
 * The database used is SQL Workbench 8.0.26.
 */
public abstract class TypeQuery
{


    /**
     * Retrieves all types from the types table and populates the typeList.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void selectAll() throws SQLException
    {

        String sql = "SELECT * FROM TYPES";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next())
        {
            int id = resultSet.getInt("Type_ID");
            String name = resultSet.getString("Name");
            String relationship = resultSet.getString("Relationship");

            Type type = new Type(id, name, relationship);
            TypeList.add(type);
        }

    }
}
